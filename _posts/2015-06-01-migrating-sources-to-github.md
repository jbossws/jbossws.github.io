---
layout:     post
title:       "Migrating sources to GitHub"
subtitle:   ""
date:       June 26, 2015
author:     JBossWS Team
---


I&#39;ve been asked multiple times why the _JBossWS_ project sources were still hosted on a _Subversion_ repository. I&#39;ve also had to put up with the complaints on time-consuming checkouts and even a bit of mockery from someone for not having migrated to _Git_ yet...  

Anyway, last week I had some quiet days, got the inspiration and started the migration... so now the _JBossWS_ sources are eventually hosted on _[GitHub](https://github.com/jbossws)_ :-)  

I&#39;ve started by creating a _jbossws_ organization on _GitHub_. Since the JBossWS project is actually a collection of multiple components each having its own lifecycle, I decided to create a repository for each of them into the organization.  

A proper migration requires importing the whole svn repository history, of course; the easiest approach to achieve that is to rely on the [GitHub importer](https://import.github.com/new). The tool worked fine for me with the smallest repositories (for instance the _jbossws-api_ and _jbossws-spi_ ones), even if it took something like 2 hours for each import (but it&#39;s nice that you can let it run on background and be notified by email when the process is completed). Unfortunately, when letting the tool process big sections of the _JBossWS_ _Subversion_ repository (like the _jbossws-cxf_ stack integration), weird import errors were eventually reported, so I had to figure out another way to perform the import.  

The alternative approach that worked is based on _git-svn_. The first step is to build up a comprehensive author mapping file linking _svn_ commit authors to _GitHub_ users. I used the following _bash_ command:  

`&gt; svn log -q | awk -F &#39;|&#39; &#39;/^r/ {sub(&#34;^ &#34;, &#34;&#34;, $2); sub(&#34; $&#34;, &#34;&#34;, $2); 
print $2&#34; = &#34;$2&#34; &lt;&#34;$2&#34;&gt;&#34;}&#39; | sort -u &gt; authors-transform.txt`

Then I created a local _Git_ repository from the Subversion sources with the following command:  

`&gt; git svn clone --stdlayout --no-metadata -A authors-transform.txt http://anonsvn.jboss.org/repos/jbossws/stack/cxf /tmp/rep`

The process still takes multiple hours and fails if a not-mapped user commit is found, but when it&#39;s completed you have a copy of all sources in a local _Git_ repository that is almost ready to push. Almost... as I still had to deal with tags because they&#39;re fetched the same as branches by the command above.  

I moved to the _/tmp/rep_ directory and started by adding a remote repository link and pushing the master:  

`&gt; git remote add origin https://github.com/jbossws/jbossws-cxf.git`  

`&gt; git push -u origin master`

Then you&#39;d have to manually create all the tags, but that&#39;s clearly unpractical if you have hundreds of them, so I googled a bit and ended up using the following two commands to generate the actual commands for pushing branches and tags respectively to the remote repository:  

`&gt; printf &#34;git push origin &#34;; git show-ref | grep refs/remotes | grep -v 
&#39;@&#39; | grep -v remotes/tags | perl -ne &#39;print 
&#34;refs/remotes/$1:refs/heads/$1 &#34; if m!refs/remotes/(.*)!&#39;; echo`

`&gt; printf &#34;git push origin &#34;; git show-ref | grep refs/remotes/tags | grep -v &#39;@&#39; | perl -ne &#39;print &#34;refs/remotes/tags/$1:refs/tags/$1 &#34; if m!refs/remotes/tags/(.*)!&#39;; echo`

I only had to clean up the output of the first command a bit as the trunk branch was clearly not to be pushed (it&#39;s the master pushed previously) and I actually did not want to push some stale branches.  

After having iterated the process above for all _JBossWS_ components that failed the automatic import process, I finally had all the sources there at _GitHub_.  

The final steps were to disable the issue tracker &amp; wiki on _GitHub_ (we already use _JIRA_ and have an equivalent to the wiki at _jboss.org_) and to invite proper users to join the Owner group for the organization as well as other specific groups that were needed.  

A day was later spent on updating the [continuous integration build environment](http://jbossws-qa.jboss.org:8180/hudson/) and the [project home page](http://jbossws.jboss.org/) to point to the new repositories... but that&#39;s not that interesting to be described in details here ;-)  

_**Enjoy the new repos, fork JBossWS and feel free to submit pull requests with your patches!**_




