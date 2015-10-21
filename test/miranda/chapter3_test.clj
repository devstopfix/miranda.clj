(ns miranda.chapter3-test
  (:require [clojure.test :refer :all]
            [miranda.chapter3 :as c3]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

; 3.1.2

(deftest test-divisor
  (is (= true  (c3/divisor? 2 4)))
  (is (= false (c3/divisor? 3 4))))

; Test all even numbers are divisible by 2
(defspec test-divisor-even 
        (prop/for-all [n (gen/such-that even? gen/s-pos-int 100) ]
          (is (c3/divisor? 2 n))))

; Test no odd numbers are divisible by 2
(defspec test-divisor-odd 
        (prop/for-all [n (gen/such-that odd? gen/pos-int 100) ]
          (is (not (c3/divisor? 2 n)))))

(deftest test-proper-divisors
  (is (= (list 1 2 3 4 6) (c3/proper-divisors 12))))

(deftest test-primes
  (is (= [2 3 5 7 11 13 17 19 23 29] (take 10 c3/primes))))

(deftest test-twin-primes
  (is (= [[3 5] [5 7] [11 13] [17 19]] (take 4 c3/twin-primes))))

; 3.1.5

(deftest test-powers-of-2-iterated
  (is (= [1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768] 
       (take 16 c3/powers-of-2-iterated)))
  (is (= 9223372036854775808N 
       (last (take 64 c3/powers-of-2-iterated)))))

(defspec test-powers-of-2
         (prop/for-all [n gen/pos-int]
                       (let [reference (take n c3/powers-of-2-iterated)]
                         (is (= reference (take n c3/powers-of-2-mapped)))
                         (is (= reference (take n c3/powers-of-2-comprehension))))))

; 3.1.5 Square root by iteration

(def approx12dp= (c3/approx= 12))

(defspec test-iterative-sqrt-is-correct-to-12dp 1e4
         (prop/for-all [n gen/s-pos-int]
                       (let [expected (Math/sqrt n)
                             actual   (c3/√ n)]
                         (is (approx12dp= expected actual)))))

(defspec test-iterative-sqrt-is-correct-to-12dp-nat 1e4
         (prop/for-all [n (gen/such-that pos? gen/nat 100)]
                         (is (approx12dp= (Math/sqrt n) (c3/√ n)))))

; 3.2.3

(defspec test-merge-sort 1e4
  (prop/for-all [xs (gen/vector gen/int)]
    (= (sort xs) (c3/merge-sort xs))))

(defspec test-quick-sort 1e4
  (prop/for-all [xs (gen/vector gen/int)]
    (= (sort xs) (c3/quick-sort xs))))
