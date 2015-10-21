(ns miranda.benchmarks.simple
  (:use [criterium.core])
  (:require [miranda.chapter3 :as c3]))

(defn benchmark-sqrt []
  (do
    (println "Math sqrt")
    (bench
      (Math/sqrt 22589))
    (println "Iterate sqrt")
      (bench
        (c3/√ 22589))))

