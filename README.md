# jbossws.github.io website

 Local testing with Jekyll
---------------------------

The website can be run locally using the Jekyll Ruby gem.
You need to fully install Ruby; on Fedora that's achieved something like this:

```
dnf install rubygem-bundler
dnf install ruby-devel
```

Then install jekyll and start the web server as follows:

```
gem install jekyll
bundle exec jekyll serve
```

Please note that depending on the Ruby version you're running, you might need to specify the version of jekyll (and all its dependencies) using `-v x.y.z` with `gem install`, so that you get a version compatible with what this web site has been developed for. You should be getting clear error message by Ruby in case of version mismatches.

Please refer to https://jekyllrb.com/ for further informations on Jekyll.
