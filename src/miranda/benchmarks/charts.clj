(ns miranda.benchmarks.charts
  (:use [incanter core charts]))

(defn t-to-µs [s]
  (let [conversion {"ms" 1000 "µs" 1}]
    (if-let [[x t units] (re-matches #"([\d.]+) (µs|ms)" s)]
      (-> (read-string t)
        (* (get conversion units)))
    0)))


(def results
  [
   ["array-sort"          (t-to-µs "0.098702 ms")]
   ["merge-sort"          (t-to-µs "2.637829 ms")]
   ["merge-sort ordered"  (t-to-µs "2.253188 ms")]
   ["merge-sort reversed" (t-to-µs "2.302531 ms")]
   ["quick-sort"          (t-to-µs "164.110072 µs")]
   ["quick-sort ordered"  (t-to-µs "934.569847 µs")]
   ["quick-sort reversed" (t-to-µs "1.080595 ms")]])


(defn -main []
  (save
    (bar-chart
      (map first results)
      (map last results)
      :y-label "Execution time mean (µs)"
      :x-label "Algorithm & dataset"
      :title "Sort Algorithms"
      :vertical false)
    "docs/sort.png"))
