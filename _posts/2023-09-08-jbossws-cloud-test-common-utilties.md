---
layout:     post
title:       "The New JBossWS Kubernetes/OpenShift Test Common Utilities"
subtitle:   ""
date:       Sep 8, 2023
author:     Jim Ma
---
When deploying a web service application to a cloud environment, such as a Kubernetes or OpenShift cluster, 
it's essential to test if everything functions as expected. We now have plenty of JBossWS tests to deploy the webservice
deployment into WildFly server and check if the webservice application function as expected.
These tests make use of various tools and frameworks, including [ShrinkWrap](https://arquillian.org/modules/shrinkwrap-shrinkwrap/) to package the deployment, 
[Arquillian](https://arquillian.org/) to manage the WFLY instance lifecycle and deploy the application deployment into WFLY server,
and a web service client for sending requests and receiving responses. 

To create tests that can run seamlessly within a Kubernetes environment, it needs to have the similar tool and framework for 
preparing the Kubernetes cluster, building container images,  deploying necessary resources (such as Services, Deployments, and Pods) to the cluster, 
and creating a client to validate the functionality of the web service running in the cloud environment.

In the following sections, we will delve into the preparation of the Kubernetes cluster, the packaging of the web service 
application into a container image, the deployment of various resources within the Kubernetes cluster, and 
the creation of a client to conduct comprehensive testing of the backend web service deployed in the Kubernetes cluster. 
Additionally, we will highlight newly developed cloud test common utilities to help create the cloud test with greater ease.

#### Prepare The Kubernetes Test Environment
To test and run the cloud tests, we need have a simple Kubernetes environment. The MiniKube is the commonly used minimal 
Kubernetes cluster for test purpose. It's easy to install and easy to start/stop. Follow [the minikube instructions](https://minikube.sigs.k8s.io/docs/start/),
to install minikube. After the minikube installation, start it with `minikube start`.
JBossWS cloud tests need to build container image and push the image to the local registry, so the minikube registry addon is required here.
`minikube addons enable registry` enables the container registry and exposes its port 5000 for local access.
After the registry is ready, the image with name and tag like `localhost:5000/wildfly-webservice:latest` can be pushed to this addon container
registry.

#### Build Container Image
Containerized application is the essential part to deploy the application to Kubernetes. 
There are a number of tools to build the container image like the Docker or CRI-O CLI tool. 
In a maven build project, it's more convenient to use a maven plugin to build the image in the maven build
lifecycle. 
The [fabric8 maven docker plugin](https://dmp.fabric8.io/) is a good fit for this job. 
Build and push images can all be completed by this maven plugin. This is an example we build the image with `docker-maven-plugin`:
```
<plugin>
    <groupId>io.fabric8</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version></version>
    <executions>
        <execution>
            <id>build-images</id>
            <phase>pre-integration-test</phase>
            <goals>
                <goal>build</goal>
                <goal>push</goal>
            </goals>
            <configuration>
                <images>
                    <image>
                        <name>localhost:5000/wildfly-webservice:latest</name>
                        <build>
                            <from>quay.io/wildfly/wildfly-runtime:latest</from>
                            <assembly>
                                <mode>dir</mode>
                                <user>jboss:root</user>
                                <targetDir>/opt/server</targetDir>
                                <inline>
                                    <formats>
                                        <format>dir</format>
                                    </formats>
                                    <fileSets>
                                        <fileSet>
                                            <directory>target/server</directory>
                                            <outputDirectory>/</outputDirectory>
                                            <includes>
                                                <include>**</include>
                                            </includes>
                                        </fileSet>
                                    </fileSets>
                                </inline>
                            </assembly>
                        </build>
                    </image>
                </images>
            </configuration>
        </execution>
    </executions>
</plugin>
```
The build maven goal is responsible for packaging the local WFLY server instance under `target/server` into image directory `/opt/server`.
The `<user>jboss:root</user>` corresponds to the USER Dockerfile directive to switch the jboss user and root group. This 
image is based on `quay.io/wildfly/wildfly-runtime:latest` and new output image is `localhost:5000/wildfly-webservice:latest`.
The push goal is taking care of the image push to the minikube's registry.

#### Deploy Kubernetes Resources
Like test against WFLY Server, the tests deploy the application deployment, JBossWS cloud tests need to deploy the various Kubernetes 
resources like and wait them ready before testing these resources are operational
as expected. In the coming JBossWS 7 release, we added these new test utils class based on [Junit5 Jupiter](https://junit.org/junit5/docs/current/user-guide/)
and [fabric8 kubernetes client](https://github.com/fabric8io/kubernetes-client) to deploy/delete Kubernetes resources before/after
cloud tests. These are the new created components in the test common utilities:

##### @JBossWSKubernetesIntegrationTest
This is the new added class to annotate the Test class and enable the JBossWSKubernetesExtension to 
deploy/undeploy the Kubernetes resources. It must be present for the JBossWS cloud test.
This annotation has only one value `kubernetesResource`
now to pass the kubernetes yaml resource file to create or delete Kubernetes resources for each test.

##### JBossWSKubernetesExtension
JBossWSKubernetesExtension is the junit5 extension and is activated by the
@JBossWSKubernetesIntegrationTest annotation. For jbossws cloud test user, although this class is 
an internal class, and user doesn't need to import or directly use this class in the cloud test, but it's good for user 
to know this class can help finishing these tasks :
- Create the kubernetes client and inject it to the test instance.
- Read the `KubernetesResource` file defined in @JBossWSKubernetesIntegrationTest annotation
- Create and delete the loaded `KubernetesResource` in @BeforeAll and @AfterAll annotated method.

##### @InjectKubeClient
This class to denote the field or the parameter to be injected with created KubernetesClient like:
`@InjectKubeClient
private KubernetesClient k8sClient;
`
or the parameter injection:
`public void  checkWSEndpoint(@InjectKubeClient KubernetesClient kubeClient) throws Exception {
`
##### JBossWSKubernetesTest
JBossWSKubernetesTest is the base class to provide the WildFly instance readiness check in the 
@BeforeEach annotated method. By checking the WildFly readiness `http://localhost:9990/health/ready`, it waits for the amount of time unless
the WildFly server is ready or the timeout . The cloud test can extend this base class to have the ability to wait for 
the WildFly server ready in the cloud environment. Here is an example of jbossws cloud test which uses these new created test common utilities classes:
```
@JBossWSKubernetesIntegrationTest(
kubernetesResource = "src/test/resources/kubernetes.yml"
)
public class EndpointTestCase extends JBossWSKubernetesTest {

    private final String APP_NAME = "jbossws-cxf-k8s-basic";
    @Test
    public void  checkWSEndpoint(@InjectKubeClient KubernetesClient kubeClient) throws Exception {
        List<Pod> lst = kubeClient.pods().withLabel("app.kubernetes.io/name", APP_NAME).list().getItems();
        Assertions.assertEquals(1, lst.size(), "More than one pod found with expected label " + lst);
        Pod first = lst.get(0);
        Assertions.assertNotNull(first, "pod isn't created");
        Assertions.assertEquals("Running", first.getStatus().getPhase(), "Pod isn't running");
        LocalPortForward p = kubeClient.services().withName(APP_NAME).portForward(8080);
        Assertions.assertTrue(p.isAlive());
        URL baseURL = new URL("http://localhost:" + p.getLocalPort() + "/" + APP_NAME + "/EndpointImpl");
        Endpoint endpoint = initPort(baseURL);
        String  echoed = endpoint.echo("from k8s pod");
        Assertions.assertEquals("Echo:from k8s pod", echoed);
    }
```
#### Check Deployed WebService
This is the client part to send the request to the deployed webservice and verify the response. 
It is quite same as the client for the deployed service in WildFly, the only difference is 
to get the accessible webservice url by invoking local port forward. The port forward means expose 
the service or pod to the local machine port that can be accessed by the created webservice in the test.
In the above example, the`LocalPortForward p = kubeClient.services().withName(APP_NAME).portForward(8080);`
expose the Kubernetes service port 8080 to a local random port like `54443`. The following service requests are 
all sent to this exposed port to call this service. 

#### Summary
In the new JBossWS release, the jbossws cloud tests will be added along with the new created test common utilities. To look at the complete jbossws
test case and the kuberentes yaml files, please go to https://github.com/jbossws/jbossws-cxf/tree/main/modules/testsuite/cloud-tests/k8s
and look at the `basic` folder for the basic could test, `wstrust` and `wsse` for the advanced webservice cloud tests.
For any issue, feedback and comments please let us know.
