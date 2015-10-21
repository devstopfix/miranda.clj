(ns miranda.charts.results)

(defn t-to-µs [s]
  (let [conversion {"ms" 1000 "µs" 1}]
    (if-let [[x t units] (re-matches #"([\d.]+) (µs|ms)" s)]
      (-> (read-string t)
          (* (get conversion units)))
      0)))

(def sort-results
  [
   ["array-sort"           (t-to-µs   "0.098702 ms")]
   ["quick-sort"           (t-to-µs "164.110072 µs")]
   ["quick-sort ordered"   (t-to-µs "934.569847 µs")]
   ["quick-sort reversed"  (t-to-µs   "1.080595 ms")]
   ["merge-sort subvec"    (t-to-µs "863.279629 µs")]
   ["merge-sort (bad alg)" (t-to-µs   "2.637829 ms")]
   ;["merge-sort ordered"  (t-to-µs   "2.253188 ms")]
   ;["merge-sort reversed" (t-to-µs   "2.302531 ms")]
   ])

(def prime-results
  [
   ["Sieved" 444.641263]
   ["Brute"  527.674280]])


