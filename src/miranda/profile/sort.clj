(ns miranda.profile.sort
  (:require [clojure.test :refer :all]
            [miranda.chapter3 :as c3]
            [clojure.test.check.generators :as gen]))


(defn ordered? [xs]
  "Returns true if the elements of the list are in order"
  (->> xs
      (partition 2 1)
       (every? (fn [[a b] &] (<= a b)))))

(defmacro timed-eval
  "Evaluates expr and prints the time it took in microseconds.  Returns the value of expr."
  [report expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr
         elapsed-ns# (double (- (. System (nanoTime)) start#))]
     (println (clojure.string/join "\t" (conj ~report (/ elapsed-ns# 1000.0))))
     ret#))

(def count-distinct-values (comp count distinct))

(defn profile-sort [f max-size n]
  "Profile the sorting function f, with a list of integers with given max-size,
   over n iterations. Writes results to stdout"
  (doseq [xs (gen/sample (gen/vector gen/int 0 max-size) n)]
    (timed-eval [(name f) (count xs) (count-distinct-values xs)] (f xs))))

; lein run -m miranda.profile.sort > docs/sort.tsv
(defn -main []
  (println (clojure.string/join \tab ["algorithm" "count" "distinct" "elapsed-µs"]))
  (profile-sort 'c3/quick-sort 128 128)
  (profile-sort 'c3/merge-sort 128 128))
