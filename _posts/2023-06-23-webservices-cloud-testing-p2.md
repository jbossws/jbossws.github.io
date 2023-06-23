---
layout:     post
title:       "Part 2, Designing a Testing Framework"
subtitle:   ""
date:       June 23, 2023
author:     Rebecca Searls
---

This is the second article in a series about the web services team's
exploration of technologies and strategies for running RESTEasy and JBossws-cxf integration tests in a cloud environment.
[Part 1](https://jbossws.github.io/2023/06/16/webservices-cloud-testing-p1/) describes the scope of this project.
This article investigates designing a testing framework based on maven.



One of the project objectives is to use maven to generate the testable components and run the tests.  For this initial evaluation there are two test components, an image file containing a web service WAR running in Wildfly and a junit test class that runs outside the image file.  Future test cases will be more complex consisting of several web service images and a single test class.

In proto-typing the testing framework an exiting simple arquillian based web service integration test will be migrated to this framework.  The complexity of the required modifications will be noted.  In my opinion the fewer the modifications the better.  I have considered creating a mock-arquillian archive that would allow the arquillian classes referenced in the exiting test class to be ignored.  This would allow the test class to remain unaltered and useable in both arquillian and the cloud test frameworks.  But it does not seem like a good idea for test clarity and long term code maintenance, so I will be making edits to the test class.

As noted in part one, I think the framework should support testing both development and released versions of Wildfly. There are 2 strategies that can be used to build the image file.  One is to use a Wildfly image provided in quary.io/wildfly and add the web service
WAR to it; creating a new image.  Two, is to generate a version of wildfly on local 
 disk.  Add the WAR file to it.  Use a base docker image and add the wildfly build to that; creating a new image.
In both test image building strategies the web service WAR file must be 
available when building the image.  With arquillian the WAR file is defined within the test class.  It is generated and deployed when the test class runs.  In this image building process the maven-war-plugin is used to generate the WAR file.  The corresponding code is removed from the test class.  Generating the WAR file in this manner also means every test must exist in its own project module.

In addressing the second strategy, generating a version of wildfly on local
disk, org.wildfly.plugins:wildfly-maven-plugin is used to generate Wildfly from a Wildfly feature pack.  To that, the WAR file is copied into wildfly's standalone/deployment directory by the plugin. The wildfly-maven-plugin has been enhanced to run Wildfly CLI-scripts.  A feature future web service cloud tests will need but is not required for this simple test case.  I find having the Wildfly code that is used in the image available locally helpful.  It makes it easy to debug any server issue with the test.


For both strategies io.fabric8:docker-maven-plugin is used to generate a docker image from a Dockerfile provided in the project.  docker-maven-plugin is a versatile plugin with many features and options for generating Docker images and configurating the image's run parameters.  This plugin can be used for testing.  It can start the image in Docker, run the test, stop the Docker instance and cleanup after the test.   However a more complex test case should be created to futher evaluate the capabilies of this plugin to meet the needs of our future test cases.

The plugin recommended using the maven-failsafe-plugin instead of maven-surefire-plugin in running the integration test because on test failure maven-failsafe-plugin will clean up the Docker process and maven-surefire-plugin will not.  I encountered an issue using maven-failsafe-plugin in place of maven-surefire-plugin.  maven-surefire-plugin runs as a default build process.  In order to enable maven-failsafe-plugin to run I had to disable the running of surefire by excluding the test class in the plugin and including the test class in the maven-failsafe-plugin.  Also maven-failsafe-plugin requires test method names to either start or end with "IT", so the test class had to be adjusted accordingly.
While editing the test class I also removed the references to arquillian annotations, RunAsClient and Deployment, and junit annotation RunWith.

A key objective of the testing framework is to be able to run the test suite on any Kuberneties compliant implementation.  io.fabric8:docker-maven-plugin does not appear to provide that versatility.  It deploys images to Docker only. However I have not found any other plugin that can deploy to any Kuberneties implementation either.

Lets review the pros and cons of this framework

Pros
  - The test can be built and run via maven.
  - The test class does not need extensive editing.

Cons
  - Each test will required its own project module because a WAR file must be created.
  - There will be a lot of duplicate boilerplate code in the pom files.
  - The test class requires some edits.
  - There is no generic support for deploying the tests to any Kuberneties implementation.

Source code for this proto-type project can be found [here.](https://github.com/rsearls/blog-posts/tree/master/blog-mvn-wfly-plugin)










 
