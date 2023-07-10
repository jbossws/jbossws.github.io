---
layout:     post
title:       "Web Services testing in the cloud - Part II"
subtitle:   ""
date:       June 23, 2023
author:     Rebecca Searls
---

This is the second article in a series about the web services team's
exploration of technologies and strategies for running JBossWS-CXF integration tests in a cloud environment.
[Part I](https://jbossws.github.io/2023/06/16/webservices-cloud-testing-p1/) describes the scope of this project.

#### Designing a Testing Framework

One of the project objectives is to use Maven to generate the testable components and run the tests.  For this initial evaluation there are two test components, an image file containing a web service deployment archive (usually a WAR file) running in WildFly and a JUnit test class that runs outside the image file.  Future test cases will be more complex consisting of several web service images and a single test class.

In proto-typing the testing framework an exiting simple Arquillian based web service integration test will be migrated to this framework.  The complexity of the required modifications will be noted.  In my opinion the fewer the modifications the better.  I have considered creating a mock-arquillian archive that would allow the Arquillian classes referenced in the exiting test class to be ignored.  This would allow the test class to remain unaltered and useable in both Arquillian and the cloud test frameworks.  But it does not seem like a good idea for test clarity and long term code maintenance, so I will be making edits to the test class.

As noted in [Part I](https://jbossws.github.io/2023/06/16/webservices-cloud-testing-p1/), I think the framework should support testing both development and released versions of WildFly. There are 2 strategies that can be used to build the image file.  One is to use a WildFly image provided in [quay.io/wildfly](https://quay.io/wildfly) and add the web service
deployment archive to it, creating a new image.  Two, is to generate a version of WildFly on local 
 disk, add the deployment archive to it, use a base _docker_ image, add the WildFly build to that and finally create a new image.
In both test image building strategies the web service deployment archive must be 
available when building the image.  With Arquillian that file is defined within the test class.  It is generated using ShrinkWrap APIs and deployed when the test class runs.  In this image building process the [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/) is used to generate the deployment archive.  The corresponding code is removed from the test class.  Generating the deployment archive in this manner also means every test must exist in its own project module.

In addressing the second strategy, generating a version of WildFly on local
disk, [org.wildfly.plugins:wildfly-maven-plugin](https://docs.wildfly.org/wildfly-maven-plugin/) is used to generate WildFly from a WildFly feature pack.  To that, the deployment archive is copied into WildFly's `standalone/deployment` directory by the plugin. The wildfly-maven-plugin has been enhanced to run WildFly CLI scripts.  A feature future web service cloud tests will need but is not required for this simple test case.  I find having the WildFly code that is used in the image available locally helpful.  It makes it easy to debug any server issue with the test.


For both strategies [io.fabric8:docker-maven-plugin](https://dmp.fabric8.io/) is used to generate a [docker](https://www.docker.com/) image from a [Dockerfile](https://docs.docker.com/engine/reference/builder/) provided in the project.  _docker-maven-plugin_ is a versatile plugin with many features and options for generating _docker_ images and configurating the image's run parameters.  This plugin can be used for testing.  It can start the image in _docker_, run the test, stop the _docker_ instance and cleanup after the test. However a more complex test case should be created to futher evaluate the capabilies of this plugin to meet the needs of our future test cases.

The plugin recommended using the [maven-failsafe-plugin](https://maven.apache.org/surefire/maven-failsafe-plugin/) instead of [maven-surefire-plugin](https://maven.apache.org/surefire/maven-surefire-plugin/) in running the integration test because on test failure _maven-failsafe-plugin_ will clean up the _docker_ process and _maven-surefire-plugin_ will not. I encountered an issue using _maven-failsafe-plugin_ in place of _maven-surefire-plugin_. _maven-surefire-plugin_ runs as a default build process. In order to enable _maven-failsafe-plugin_ to run I had to disable the running of surefire by excluding the test class in the plugin and including the test class in the _maven-failsafe-plugin_. Also maven-failsafe-plugin requires test method names to either start or end with "IT", so the test class had to be adjusted accordingly.
While editing the test class I also removed the references to Arquillian annotations, _RunAsClient_ and _Deployment_, and JUnit annotation _RunWith_.

A key objective of the testing framework is to be able to run the test suite on any Kubernetes compliant implementation. _io.fabric8:docker-maven-plugin_ does not appear to provide that versatility.  It deploys images to _docker_ only. However I have not found any other plugin that can deploy to any Kubernetes implementation either.

Let's review the pros and cons of this framework:

*Pros*
  - The test can be built and run via maven.
  - The test class does not need extensive editing.

*Cons*
  - Each test will required its own project module because a deployment archive must be created.
  - There will be a lot of duplicate boilerplate code in the _pom.xml_ files.
  - The test class requires some edits.
  - There is no generic support for deploying the tests to any Kubernetes implementation.

Source code for this proto-type project can be found [here](https://github.com/rsearls/blog-posts/tree/master/blog-mvn-wfly-plugin).










 
