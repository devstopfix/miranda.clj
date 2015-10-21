(ns miranda.benchmarks.primes
  (:use [criterium.core])
  (:require [miranda.chapter3 :as c3]))


; Find the 2,818th prime number
; 25589 = 25² + 158²
(defn benchmark-primes []
  (do
    (println "Sieved primes")
    (bench
      (last (c3/sieved-primes)))
    (println "Primes")
    (println
      (bench (take 2818 (c3/sieved-primes))))))

