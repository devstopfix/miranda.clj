(defproject miranda "1.3.3"
  :description "Functional Programming with Miranda in Clojure"
  :url "https://github.com/devstopfix/miranda.clj"
  :license {:name "The MIT License (MIT)"
            :url "http://opensource.org/licenses/MIT"}
  :jvm-opts ["-Xmx2g"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/test.check "0.8.2"]
                 [criterium "0.4.3"]
                 [incanter/incanter-core "1.5.6"]
                 [incanter/incanter-charts "1.5.6"]])
