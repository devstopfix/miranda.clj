; Comments are quotations from the book 
; "Functional Programming with Miranda"
; ©1991 Dr Ian Holyer

(ns miranda.chapter3 "3. The Design of Functions")

; 3.1.2 Filtering

(defn divisor? 
  "Returns true if x is a divisor of n"
  [x n]
  (zero? (mod n x)))

(defn proper-divisors [n]
  "Returns a list of the divisors of n (including 1 but excluding n)"
  (filter #(divisor? % n) (range 1 (inc (/ n 2)))))

(def primes
  "Seq of prime numbers. Inefficient!" 
  (filter 
    #(= (list 1) (proper-divisors %))
    (drop 2 (range))))

(def twin-primes
  "Seq of twin primes (pair of primes that differ by 2)"
  (->> primes
    (partition 2 1)
    (filter (fn [[a b]] (= 2 (- b a))))))