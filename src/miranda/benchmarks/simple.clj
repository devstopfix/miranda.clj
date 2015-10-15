(ns miranda.benchmarks.simple
  (:require [miranda.chapter3 :as c3]))

;(time (last (take 2818 c3/primes)))
;"Elapsed time: 0.554657 msecs"
;=> 25589

;(time (last (c3/sieved-primes)))
;"Elapsed time: 591.178561 msecs"
;=> 25589

