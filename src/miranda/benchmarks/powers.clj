(ns miranda.benchmarks.powers
  (:use [criterium.core])
  (:require [miranda.chapter3 :as c3]))

; https://en.wikipedia.org/wiki/C0_and_C1_control_codes#Field_separators
(def RS "\u001E")

(defn bench-take-n-items-of-xs [n xs f]
  (do
    (println (format "%s\n%s (n=%d)" RS f n))
    (bench
      (last (take n xs))
      :os :runtime)))

(defn benchmark-powers []
  (doseq [n [10 100 1000]]
    (bench-take-n-items-of-xs n c3/powers-of-2-iterated      (name 'c3/powers-of-2-iterated))
    (bench-take-n-items-of-xs n c3/powers-of-2-mapped        (name 'c3/powers-of-2-mapped))
    (bench-take-n-items-of-xs n c3/powers-of-2-comprehension (name 'c3/powers-of-2-comprehension))))
