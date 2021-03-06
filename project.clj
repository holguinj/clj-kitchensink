(defproject puppetlabs/kitchensink "1.1.1-SNAPSHOT"
  :description "Clojure utility functions"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}

  ;; Abort when version ranges or version conflicts are detected in
  ;; dependencies. Also supports :warn to simply emit warnings.
  ;; requires lein 2.2.0+.
  :pedantic? :abort

  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; Logging
                 [org.clojure/tools.logging "0.2.6"]
                 ;; Filesystem utilities
                 [me.raynes/fs "1.4.5"]
                 ;; Configuration file parsing
                 [org.ini4j/ini4j "0.5.2"]
                 [org.clojure/tools.cli "0.3.0"]
                 ;; This library is used by puppetlabs.kitchensink.classpath
                 ;; to do some classpath stuff.
                 [org.tcrawley/dynapath "0.2.3"]
                 [digest "1.4.3"]
                 [clj-time "0.5.1"]
                 [slingshot "0.10.3"]
                 ;; JSON
                 [cheshire "5.3.1"]]

  ;; By declaring a classifier here and a corresponding profile below we'll get an additional jar
  ;; during `lein jar` that has all the code in the test/ directory. Downstream projects can then
  ;; depend on this test jar using a :classifier in their :dependencies to reuse the test utility
  ;; code that we have.
  :classifiers [["test" :testutils]]

  :profiles {:dev {:dependencies [[spyscope "0.1.4"]]
                   :injections [(require 'spyscope.core)]}
             :testutils {:source-paths ^:replace ["test"]}}

  ;; this plugin is used by jenkins jobs to interrogate the project version
  :plugins [[lein-project-version "0.1.0"]
            [lein-release "1.0.5"]]

  :lein-release        {:scm          :git
                        :deploy-via   :lein-deploy}

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/clojars_jenkins_username
                                     :password :env/clojars_jenkins_password
                                     :sign-releases false}]
                        ["snapshots" "http://nexus.delivery.puppetlabs.net/content/repositories/snapshots/"]])
