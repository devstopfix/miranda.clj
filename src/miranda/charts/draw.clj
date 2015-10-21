(ns miranda.charts.draw
  (:use [incanter core charts]
        [miranda.charts.results])
  (:import (java.awt Color)))

(def miranda-blue (Color/decode "#0AB7BE"))

(defn sqrt-chart [results]
  (doto
    (bar-chart
      (map first results)
      (map last  results)
      :y-label "Execution time mean (µs)"
      :x-label "Algorithm"
      :title "Square Root"
      :vertical false)
    (set-stroke-color miranda-blue)
    (save "docs/sqrt.png")))

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

(defn powers-chart [results]
  (doto
    (bar-chart
      (map first results)
      (map last  results)
      :y-label "Execution time mean (µs)"
      :x-label "Algorithm"
      :title "Generated seq of powers of 2"
      :vertical false)
    (set-stroke-color miranda-blue)
    (save "docs/powers.png")))

(defn prime-chart []
  (doto
    (bar-chart
      (map first prime-results)
      (map last prime-results)
      :y-label "Execution time mean (ms)"
      :x-label "Algorithm"
      :title "Prime Number Algorithms"
      :vertical false)
      (set-stroke-color miranda-blue)
      (save "docs/prime.png")))

(defn -main []
  (do
    (sqrt-chart sqrt-results)
    (sort-chart)
    (powers-chart powers-1e4-results)
    (prime-chart)))
