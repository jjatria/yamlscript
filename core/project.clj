;; This code is licensed under MIT license (See License for details)
;; Copyright 2023-2024 Ingy dot Net

(defproject yamlscript/core "0.1.34"
  :description "Program in YAML"

  :url "https://github.com/yaml/yamlscript"

  :license
  {:name "MIT"
   :url "https://opensource.org/license/mit/"}

  :scm
  {:name "git"
   :url "https://github.com/yaml/yamlscript"
   :tag "clojure"
   :dir ".."}

  :dependencies
  [[org.clojure/clojure "1.11.1"]
   [org.clojure/data.json "2.4.0"]
   [clj-commons/clj-yaml "1.0.27"]
   [org.snakeyaml/snakeyaml-engine "2.7"]
   [org.babashka/sci "0.8.41"]]

  :plugins
  [[lein-exec "0.3.7"]
   [reifyhealth/lein-git-down "0.4.1"]
   [dev.weavejester/lein-cljfmt "0.11.2"]
   [io.github.borkdude/lein-lein2deps "0.1.0"]]

  :prep-tasks [["lein2deps" "--write-file" "deps.edn" "--print" "false"]]

  :repositories [["public-github" {:url "git://github.com"}]]

  :global-vars {*warn-on-reflection* true}

  :profiles
  {:dev
   {:dependencies
    [[pjstadig/humane-test-output "0.11.0"]]
    :injections [(require 'pjstadig.humane-test-output)
                 (pjstadig.humane-test-output/activate!)]}

   :repl
   {:repl-options
    {:init
     (do
       (require 'pjstadig.humane-test-output)
       (pjstadig.humane-test-output/activate!)
       (require 'yamlscript.test-runner))}}})
