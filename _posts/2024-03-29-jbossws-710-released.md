---
layout:     post
title:      "JBossWS 7.1.0.Final is released!"
subtitle:   ""
date:       Mar 27, 2024
author:     Jim Ma
---
JBossWS-CXF 7.1.0.Final has been released. This release contains several bug fixes, test enhancements and components upgrades.
The notable upgrades are the CXF 4.0.4 upgrade which resolved [CVE-2024-28752](https://nvd.nist.gov/vuln/detail/CVE-2024-28752)
and XmlSec(santuario) 3.0.3 upgrade resolved [CVE-2023-44483](https://nvd.nist.gov/vuln/detail/CVE-2023-44483). The CXF upgrade introduced the new CXF http client which is using java.net.http.HttpClient from JDK to better support HTTP/2.From 7.1.0.Final,
we started to use Java 21 to build and run testsuite to make sure everything works with this latest version JDK.

For more detailed info and full list of issue in this release, please check [release notes](https://issues.redhat.com/secure/ReleaseNote.jspa?projectId=12310050&version=12414901).
Please try this release out and give us your feedback!