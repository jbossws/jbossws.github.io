---
layout:     post
title:      "JBossWS 7.4.1.Final is released!"
subtitle:   ""
date:       August 12, 2026
author:     Fabio Burzigotti
---

In this release we upgraded Apache CXF to 4.1.8, which includes a security improvement that blocks decoupled
WS-Addressing destinations by default as a hardening measure against SSRF attacks. JBossWS-CXF enables decoupled
WS-Addressing destinations by default to preserve backward compatibility, while allowing users to opt out and
leverage the new Apache CXF security behaviour via the `org.jboss.ws.cxf.decoupledEndpointEnabled` property.

This release also marks the point where the `7.4.x` maintenance branch will be created in the jbossws-cxf repository,
as development moves on to the next major release. See the [GitHub discussion](https://github.com/jbossws/jbossws-cxf/discussions/733) for more details.

Please try out this release, and let us know if you have any issues or questions.

### Release notes - JBoss Web Services - jbossws-cxf-7.4.1.Final

Full release report: [JBWS 7.4.1.Final](https://redhat.atlassian.net/projects/JBWS/versions/109929/tab/release-report-all-issues)

#### Component Upgrade

- [JBWS-4521](https://redhat.atlassian.net/browse/JBWS-4521) - Upgrade Apache CXF from 4.1.6 to 4.1.8

- [JBWS-4522](https://redhat.atlassian.net/browse/JBWS-4522) - Upgrade com.google.cloud.tools:jib-maven-plugin from 3.4.3 to 3.5.2

- [JBWS-4523](https://redhat.atlassian.net/browse/JBWS-4523) - Upgrade org.apache.logging.log4j:log4j-1.2-api from 2.23.1 to 2.26.1

- [JBWS-4524](https://redhat.atlassian.net/browse/JBWS-4524) - Upgrade org.apache.neethi:neethi from 3.2.2 to 3.2.3

#### Task

- [JBWS-4525](https://redhat.atlassian.net/browse/JBWS-4525) - \(jbossws-cxf\) - Update the project README to reflect the current release procedure
