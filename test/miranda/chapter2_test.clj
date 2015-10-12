(ns miranda.chapter2-test
  (:require [clojure.test :refer :all]
            [miranda.chapter2 :as c2]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(deftest test-squares-of-first-10-even-numbers
  (is (= [4 16 36 64 100]
    c2/squares-of-first-10-even-numbers)))

(deftest test-list-constructors
  (let [[n & ns] [2 4 6]]
    (is (= 2 n))
    (is (= [4 6] ns))))