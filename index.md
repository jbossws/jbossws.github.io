---
layout:     main
title:      "JBossWS 7.4.1.Final is released!"
date:       August 12, 2026
author:     Fabio Burzigotti
---
JBossWS-CXF 7.4.1.Final has been released. In this release we upgraded Apache CXF to 4.1.8, which includes a
security improvement that blocks decoupled WS-Addressing destinations by default as a hardening measure against
SSRF attacks. JBossWS-CXF enables decoupled WS-Addressing destinations by default to preserve backward
compatibility, while allowing users to opt out and leverage the new Apache CXF security behaviour via the
`org.jboss.ws.cxf.decoupledEndpointEnabled` property.

This release also marks the point where the `7.4.x` maintenance branch will be created in the jbossws-cxf repository,
as development moves on to the next major release. See the [GitHub discussion](https://github.com/jbossws/jbossws-cxf/discussions/733) for more details.

See more details in [the last Blog post]({% post_url 2026-08-12-jbossws-741-release %})

Please try out this release, and let us know if you have any issues or questions.
