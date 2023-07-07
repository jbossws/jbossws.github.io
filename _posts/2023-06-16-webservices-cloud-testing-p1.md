---
layout:     post
title:       "Web Services testing in the cloud - Part I"
subtitle:   ""
date:       June 16, 2023
author:     Rebecca Searls
---
The web services team is embarking on an exploration of technologies and strategies
for running the JBossWS-CXF integration tests in a cloud environment.
A series of blogs will follow that discuss the technologies
evaluated and strategies tried in this quest.

#### Working on requirements

##### Test execution architecture

It is the team's desire to use a framework that adheres to the Java maxim 
_"Write once. Run anywhere"_.  In this case, write the tests once and run them on
any Kubernetes based cloud environment.
I would add to that, the goal of leveraging what we can of the existing test
class format.  The web services integration test suites uses Arquillian / [ShrinkWrap](https://github.com/shrinkwrap)
which provides a means for the test class itself to contain the definition of the
web service deployment archive (normally a WAR file) that is deployed and the client code used to call the service.
While I find it a convenience to have both key components co-located in a single file,
the team is still discussing whether the test suite shouldn't instead follow the
classic cloud paradigm in which a testable image is created in a separate pre-test build step 
and the deployment archive would need to be available at that time in order to be
incorporated into the testing image.

Generally it is desirable to continue using Maven and its plugins to build the test images 
and run the test suites. As a matter of fact, Maven has been the dominant build tool of Red Hat shephered projects
for years.

##### Test environment

At this time there are two environments in which we must be able to run the
test suite, one on our local machines for development purposes and two in the CIs we use.
This means running [minikube](https://minikube.sigs.k8s.io/docs/) and/or [OpenShift](https://docs.openshift.com/) on our personal machines.

JBossws-CXF continuous integration is currently run on GitHub.  It is yet to be determined what cloud
products GitHub CI can run. Jenkins supports testing in cloud environments but our team does
not currently use it.

[WildFly](https://www.wildfly.org/) will be the server used in a testable image.  In my opinion the test suite
should support generation of testable images of both development versions of WildFly and
released versions.  The WildFly team pushes images of released versions
of WildFly to the [quay.io](https://quay.io/) repository.  An extra build step will be needed to generate an image containing a development version of WildFly.

##### Test scenarios

Another issue under discussion is do we write only new tests or leverage
some or all the existing tests.  There is agreement that new tests need
to be written.  There are unique cloud based scenarios that should be
tested.  There is still an open discussion about testing any of the existing
integration tests in the cloud.  There is also a discussion whether the suite
of tests should reside in a separate project or in a new module within the existing projects.  I think the test suite location
will be determined once we have a better understanding of the framework(s)
for building and running the tests in the required environments.

These are the general parameters the team is using in evaluating technologies and strategies in writing and running web services tests in the cloud.  The coming blogs
will discuss what we are learning and the decisions made for our implementation.
If you're willing to participate to the discussion or getting involved and contribute to effort in any way, feel free to reach out, for example by starting a thread in [our discussion space on GitHub](https://github.com/jbossws/jbossws-cxf/discussions).

The next part of this blog post series is available [here](https://jbossws.github.io/2023/06/23/webservices-cloud-testing-p2/).
