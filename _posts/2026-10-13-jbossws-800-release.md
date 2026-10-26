---
layout:     post
title:      "JBossWS 8.0.0.Final is released!"
subtitle:   ""
date:       October 13, 2026
author:     Fabio Burzigotti
---

This release upgrades key security components — Apache CXF to 4.1.9 and WSS4J to 4.0.2 — resolving multiple CVEs, along with OpenSAML 5.2.3. It aligns with WildFly 41.0.1.Final and sets Java SE 17 as the minimum supported runtime.

Please try out this release, and let us know if you have any issues or questions.

### Release notes - JBoss Web Services - jbossws-cxf-8.0.0.Final

#### Component Upgrade

- [JBWS-4535](https://redhat.atlassian.net/browse/JBWS-4535) - Upgrade WSS4J from 4.0.1 to 4.0.2 \(as required by CXF 4.1.9\) and related dependencies, most notably OpenSAML \(5.1.6 -> 5.2.3\)

  - Upgrade WSS4J dependencies from 4.0.1 to 4.0.2 \(resolves CVE-2026-88920\)

- [JBWS-4538](https://redhat.atlassian.net/browse/JBWS-4538) - Upgrade Apache CXF components from 4.1.8 to 4.1.9, and align relevant dependencies, except WSS4J

  - Upgrade Apache CXF dependencies from 4.1.8 to 4.1.9 \(resolves CVE-2026-78384, CVE-2026-108039, CVE-2026-107937 in cxf-core and CVE-2026-97791, CVE-2026-97468 in cxf-rt-ws-security\)

- [JBWS-4539](https://redhat.atlassian.net/browse/JBWS-4539) - Upgrading WildFly to 41.0.1.Final

  - Upgrade WildFly from 40.0.0.Final to 41.0.1.Final

#### Task

- [JBWS-4469](https://redhat.atlassian.net/browse/JBWS-4469) - JBossWS Galleon feature pack - Set SE 17 as the supported base
