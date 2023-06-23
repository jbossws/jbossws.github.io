---
layout:     post
title:       "Web Services Testing In The Cloud Part 1"
subtitle:   ""
date:       June 16, 2023
author:     Rebecca Searls
---
The web services team is embarking on an exploration of technologies and strategies
for running RESTEasy and JBossws-cxf integration tests in a cloud environment.
A series of blogs will follow that discuss the technologies
evaluated and strategies tried in this quest.

It is the team's desire to use a framework that adheres to the Java maxim,
"Write once. Run anywhere".  In this case, write the tests once and run them on
any Kubernetes based cloud environment.
I would add to that, the goal of leveraging what we can of the existing test
class format.  The web services integration test suites uses Arquillian
which provides a means for the test class itself to contain the definition of the
web service WAR file that is deployed and the client code used to call the service.
I find it a convenience to have both key components co-located in a single file.
My colleagues disagree with this goal.
In the cloud paradigm a testable image is created in a separate pre-test build step.
The WAR file would need to be available at that time in order to be
incorporated into the testing image.  It is my colleagues opinion our test
suites should follow this cloud convention.

It is desirable to continue using maven and maven plugins to build the test images 
and run the test suites.  Maven has been the dominant build tool of RedHat projects
for years.  We want to continue using it for this work.

At this time there are two environments in which we must be able to run the
test suite, one on our local machines for development purposes and two in the CIs we use.  This means running minikube and/or OpenShift on our personal machines.  Both RESTEasy
and JBossws-cxf use git's CI for testing.  It is yet to be determined what cloud
products git CI can run.  Jenkins supports testing in cloud environments but our team does
not currently use it.

Wildfly will be the server used in a testable image.  In my opinion the test suite
should support generation of testable images of both development versions of Wildfly and
released versions.  The Wildfly team pushes images of released versions
of Wildfly to the quary.io repository.  An extra build step will be needed to generate an image containing a development version of Wildfly.

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

[Part 2:](https://jbossws.github.io/2023/06/23/webservices-cloud-testing-p2/) Designing a Testing Framework