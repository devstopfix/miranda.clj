(ns miranda.benchmarks.powers
  (:use [criterium.core])
  (:require [miranda.chapter3 :as c3]))

(def MAX (int 1e4))

(defn benchmark-powers []
  (do
    (println "Powers 2 iterated")
    (bench
      (last (take MAX c3/powers-of-2-iterated)))
    (println "Powers 2 list comprehension")
    (bench
      (last (take MAX c3/powers-of-2-comprehension)))
    (println "Powers 2 mapped")
    (bench
      (last (take MAX c3/powers-of-2-mapped)))))
