(ns miranda.chapter1-test
  (:require [clojure.test :refer :all]
            [miranda.chapter1 :as c1]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

; 1.2

(deftest test-square
  (is (= 9 (c1/square 3))))

(defspec test-square 
  (prop/for-all [n (gen/one-of [gen/nat gen/int]) ]
    (let [x (c1/square n)]
      (is (= (* n n) x)))))

; 1.3.1

(defspec test-fourth-power 
  (prop/for-all [n (gen/one-of [gen/nat gen/int]) ]
    (let [x (c1/fourth-power n)]
      (is (= (* n n n n) x)))))

; 1.3.3

(deftest test-square-is-polymorphic
  (is (= 4   (c1/square 2)))
  (is (= 9.0 (c1/square 3.0))))

(deftest test-squares 
  (is (= 100 (last (take 11 c1/squares)))))