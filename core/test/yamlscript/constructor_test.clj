; Copyright 2023-2024 Ingy dot Net
; This code is licensed under MIT license (See License for details)

(ns yamlscript.constructor-test
  (:use yamlscript.debug)
  (:require
   [clojure.edn :as edn]
   [yamlscript.parser :as parser]
   [yamlscript.composer :as composer]
   [yamlscript.resolver :as resolver]
   [yamlscript.builder :as builder]
   [yamlscript.transformer :as transformer]
   [yamlscript.constructor :as constructor]
   [yamltest.core :as test]))

(test/load-yaml-test-files
  ["test/compiler-stack.yaml"
   "test/data-mode.yaml"
   "test/compiler.yaml"]
  {:pick-func #(test/has-keys? [:yamlscript :construct] %)
   :test-func (fn [test]
                (->> test
                  :yamlscript
                  parser/parse
                  composer/compose
                  resolver/resolve
                  builder/build
                  transformer/transform
                  constructor/construct))
   :want-func (fn [test]
                (->> test
                  :construct
                  edn/read-string))})
