---
layout:     post
title:       "JBossWS Galleon Feature Pack"
subtitle:   ""
date:       Apr 18, 2023
author:     Jim Ma
---
The WildFly Galleon project is designed to allow users to install, uninstall, and patch products with a high degree of 
customization. By defining an XML, CLI, or Maven plugin, users can slim down the WildFly server and select the subsystems 
they want to install to run their application. For example, if a user's application only requires JAX-RS, JPA, and 
Servlets and does not need EJB or JMS, the Galleon tool can help install a customized version of WildFly or EAP that 
only contains JAX-RS, JPA, Undertow, and other required dependent subsystems. Behind the WildFly Galleon technology, 
a feature pack provides the unit of WildFly that can be installed or uninstalled using Galleon tools. 
A feature pack is zipped and deployed to the Maven repository, and the Galleon tool can consume 
the feature pack as user-defined to install these units. 
In this post, we will discuss the new JBossWS CXF feature pack in the upcoming 6.3.0 release and how we can use this 
feature pack to install the new JBossWS CXF version to a WildFly server. 

#### JBossWS CXF Feature Pack

The JBossWS CXF feature pack contains all JBossWS packages to install in a WildFly server. All JBossWS related JBoss 
modules will be created or updated. With this JBossWS feature pack, the JBossWS version can be updated to 
the version in this feature pack. The JBossWS feature pack depends on the wildfly-ee-galleon-pack's latest version. 
When the JBossWS-CXF feature pack is installed, the  wildfly-ee-galleon-pack will be installed before the
JBossWS CXF feature pack.

#### Install JBossWS-CXF Feature Pack With CLI

Galleon provides a CLI tool to install and customize the WildFly installation. Simply download the latest version of
[Galleon 5.1.0.Final](https://github.com/wildfly/galleon/tree/5.1.0.Final), unzip it, and the Galleon CLI tool is 
ready for use. Then, we can install the JBossWS CXF feature pack with this command:

```
path/to/galleon-5.1.0.Final/bin/galleon.sh  install org.jboss.ws.cxf:jbossws-cxf-feature-pack:6.3.1-SNAPSHOT --dir=path/to/installation
```
The `galleon.sh install` can be run with `--layers` flag to define the subsystems you want to install. For more details,
please check [Galleon guide](https://docs.wildfly.org/27/Galleon_Guide.html).

Please note that Galleon requires Java 11 for runtime, and you should make sure that JDK11 or a higher version is set up properly.

#### Install JBossWS CXF Feature Pack with Maven

Another way to install the JBossWS-CXF feature pack is by using Maven. The `provision` goal in wildfly-maven-plugin installs the 
WildFly server in the Maven build. Here is an example to install the JBossWS CXF feature pack and provide only the features and packages 
included in the in `cloud-server` and `webservices` layers:
```
        <plugins>
            <plugin>
                <groupId>org.wildfly.plugins</groupId>
                <artifactId>wildfly-maven-plugin</artifactId>
                <version>4.1.0.Beta5</version>
                <configuration>

                    <feature-packs>
                        <feature-pack>
                            <location>org.wildfly:wildfly-galleon-pack:27.0.1.Final</location>
                        </feature-pack>
                        <feature-pack>
                            <location>org.jboss.ws.cxf:jbossws-cxf-feature-pack:6.2.1-SNAPSHOT</location>
                            <inherit-packages>true</inherit-packages>
                        </feature-pack>
                    </feature-packs>
                    <layers>
                        <layer>cloud-server</layer>
                        <layer>webservices</layer>
                    </layers>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>provision</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
```
The `wildfly-maven-plugin`'s `provision` goal offers a lot of flexibility to Maven projects for 
testing with the WFLY server and updating specific units within it. In the JBossWS project, this plugin 
is currently being utilized to build a customized version of the WildFly server. This new server is 
then used to execute all tests with the latest JBossWS CXF version installed, ensuring a reliable 
and up-to-date testing environment.

#### Summary

Galleon is a good tool for reducing the size of the WildFly server distribution. When install with the 
jbossws-cxf-feature pack, it simplifies the process of replacing and updating the JBossWS unit in WildFly. 
If you're looking to remove specific subsystems or units from your WildFly distribution and install a new 
version of the WildFly unit, it's best to avoid doing it manually. Instead, make use of Galleon or the 
wildfly-maven-plugin to ensure an efficient process.
   

 
  