(ns miranda.chapter3-test
  (:require [clojure.test :refer :all]
            [miranda.chapter3 :as c3]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(deftest test-divisor
  (is (= true  (c3/divisor? 2 4)))
  (is (= false (c3/divisor? 3 4))))

; Test all even numbers are divisible by 2
(defspec test-divisor-even 
        (prop/for-all [n (gen/such-that even? gen/s-pos-int) ]
          (is (c3/divisor? 2 n))))

; Test no odd numbers are divisible by 2
(defspec test-divisor-odd 
        (prop/for-all [n (gen/such-that odd? gen/pos-int) ]
          (is (not (c3/divisor? 2 n)))))

(deftest test-proper-divisors
  (is (= (list 1 2 3 4 6) (c3/proper-divisors 12))))

(deftest test-primes
  (is (= [2 3 5 7 11 13 17 19 23 29] (take 10 c3/primes))))

(deftest test-twin-primes
  (is (= [[3 5] [5 7] [11 13] [17 19]] (take 4 c3/twin-primes))))
