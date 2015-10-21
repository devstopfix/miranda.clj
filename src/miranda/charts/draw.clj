(ns miranda.charts.draw
  (:use [incanter core charts]
        [miranda.charts.results])
  (:import (java.awt Color)))

(def miranda-blue (Color/decode "#0AB7BE"))

(defn sort-chart []
  (doto
    (bar-chart
      (map first sort-results)
      (map last sort-results)
      :y-label "Execution time mean (µs)"
      :x-label "Algorithm & dataset"
      :title "Sort Algorithms"
      :vertical false)
    (set-stroke-color miranda-blue)
    (save "docs/sort.png")))

(defn prime-chart []
  (doto
    (bar-chart
      (map first prime-results)
      (map last prime-results)
      :y-label "Execution time mean (ms)"
      :x-label "Algorithm & dataset"
      :title "Prime Number Algorithms"
      :vertical false)
      (set-stroke-color miranda-blue)
      (save "docs/prime.png")))

(defn -main []
  (do
    (sort-chart)
    (prime-chart)))
