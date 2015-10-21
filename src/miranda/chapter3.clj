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

; 3.1.5 Iteration

(def TWO (BigInteger/valueOf 2))

(defn big-pow
  "Returns 2^n (big integer)"
  [n]
  (.pow TWO n))

(def powers-of-2-comprehension
  "Return lazy seq of the powers of 2"
  (for [n (range)] (big-pow n)))

(def powers-of-2-mapped
  "Return lazy seq of the powers of 2"
  (map big-pow (range)))

(def powers-of-2-iterated
  "Returns a seq of the powers of 2 (generated by iteration)"
  (iterate (partial *' 2) 1))

; Square root

(defn approx=
  "Return a function than returns true if a ~= b to n decimal places"
  [n]
  (let [delta (Math/pow 10 (- n))]
    (fn
      ([a b] (< (Math/abs (- b a)) delta))
      ([ab] (let [[a b] ab] (< (Math/abs (- b a)) delta))))))

(defn limit
  "Takes a sequence of numbers, and returns the last value where successive pairs are equal to 12 decimal places"
  [ns]
  (let [ne (comp not (approx= 12))]
  (->> (partition 2 1 ns)
       (drop-while ne)
       (first)
       (last))))

(defn improve
  "Takes the approximation r to the square root of n
   and returns a better approximation (Newton-Raphson)"
  [n r]
  (/ (+ r (/ n r)) 2))

(defn √
  "Calculate approximate square root of n by iteration"
  [n]
  (let [guess (/ n 2.0)]
    (limit (iterate (partial improve n) guess))))

; 3.2.2 Streams

(defn sieved-primes
  "Return a vector of primes from 2-25,589, generated by sieve.
   This algorithm is memory intensive, and you risk a StackOverflowError
   if you ask for primes higher than 25,589."
  []
  (loop [accumulator []
           candidates  (range 2 (inc 25589))]
    (if-let [p (first candidates)]
      (recur (conj accumulator p) (filter #(not= 0 (mod % p)) (rest candidates)))
      accumulator)))

; 3.2.3 General Recursion

;  merge [] y = y
;  merge (a:x) [] = a:x
;  merge (a:x) (b:y) = a:merge x (b:y), if a<=b
;                    = b:merge (a:x) y, otherwise

(defn merge-lists [xs ys]
  "Interleaving the elements of two ordered lists, in such a way that the combined list is ordered"
 (cond 
    (empty? xs) ys
    (empty? ys) xs
    :else
    (let [a (first xs) b (first ys)]
      (if (<= a b)
        (cons a (merge-lists (rest xs) ys))
        (cons b (merge-lists xs (rest ys)))))))

(defn merge-sort
  "Merge sort a vector"
  [xs]
  (if (<= (count xs) 1)
    xs
    (let [mid (/ (count xs) 2)]
      (merge-lists (merge-sort (take mid xs)) (merge-sort (drop mid xs))))))
; TODO efficient sub-vectors (merge-lists (merge-sort (subvec xs 0 mid)) (merge-sort (subvec xs mid (count xs)))))))


; Note: using the leftmost item (the head) as the pivot causes
; worst case behaviour for already sorted lists - but suits
; our use of lazy sequences.
(defn quick-sort
  "Sort a seq using the quick sort algorithm. Uses the head as the pivot."
  [xs]
  (if (seq xs) 
    (let [pivot (first xs)]
      (concat 
        (quick-sort (filter #(< % pivot) xs))
        (filter #(= % pivot) xs)
        (quick-sort (filter #(> % pivot) xs))))
    []))
