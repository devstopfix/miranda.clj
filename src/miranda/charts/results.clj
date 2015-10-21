(ns miranda.charts.results)

(defn t-to-µs [s]
  (let [conversion {"ms" 1000 "µs" 1 "ns" 0.001}]
    (if-let [[x t units] (re-matches #"([\d.]+) (µs|ms|ns)" s)]
      (-> (read-string t)
          (* (get conversion units)))
      0)))

(def sort-results
  [["array-sort" (t-to-µs "0.098702 ms")]
   ["quick-sort" (t-to-µs "164.110072 µs")]
   ["quick-sort ordered" (t-to-µs "934.569847 µs")]
   ["quick-sort reversed" (t-to-µs "1.080595 ms")]
   ["merge-sort subvec" (t-to-µs "863.279629 µs")]
   ["merge-sort (bad alg)" (t-to-µs "2.637829 ms")]
   ;["merge-sort ordered"  (t-to-µs   "2.253188 ms")]
   ;["merge-sort reversed" (t-to-µs   "2.302531 ms")]
   ])

(def prime-results
  [["Sieved" 444.641263]
   ["Brute" 527.674280]])

(def sqrt-results
  [["Math/sqrt"      (t-to-µs  "3.334454 ns")]
   ["Newton-Raphson" (t-to-µs "58.136074 µs")]])

(def powers-1e4-results
  [["Iterated"           (t-to-µs "874.452836 µs")]
   ["List comprehension" (t-to-µs   "1.196907 ms")]
   ["Mapped function"    (t-to-µs   "1.200360 ms")]])

(def powers-1e5-results
  [["Iterated (1e4)"           (t-to-µs "874.452836 µs")]
   ["Iterated (1e5)"           (t-to-µs  "11.947524 ms")]
   ["List comprehension (1e4)" (t-to-µs   "1.196907 ms")]
   ["List comprehension (1e5)" (t-to-µs  "11.659372 ms")]
   ["Mapped function (1e4)"    (t-to-µs   "1.200360 ms")]
   ["Mapped function (1e5)"    (t-to-µs  "27.647506 ms")]])
