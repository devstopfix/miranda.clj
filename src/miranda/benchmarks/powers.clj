(ns miranda.benchmarks.powers
  (:use [criterium.core])
  (:require [miranda.chapter3 :as c3]))

(defn benchmark-powers []
  (doseq [n [10 100 1000]]
    (println (format "Powers 2 iterated - %d iterations" n))
    (bench
      (last (take n c3/powers-of-2-iterated)) :os)
    (println (format "Powers 2 list comprehension - %d iterations" n))
    (bench
      (last (take n c3/powers-of-2-comprehension)))
    (println (format "Powers 2 mapped - %d iterations" n))
    (bench
      (last (take n c3/powers-of-2-mapped)))))
