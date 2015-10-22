(ns miranda.charts.scatter
  (:use [incanter core charts io stats]
        [miranda.charts.results]))
  ;(:import (java.awt Color))))


(defn tsv-dataset [name]
  "Read dataset delelimted with tabs, with header row."
  (read-dataset name :delim \tab :header true ))

(defn max-value [field q ds]
  "Return the maximum value in the quantile (e.g. q=0.99)
   for field in dataset.
   e.g. (max-value :elapsed 0.99 data)"
  (-> ($ field ds)
    (quantile :probs [q])
    (last)))

(defn filter-outliers [field ds]
  "Remove outliers from dataset (99th percentile).
   May be necessary to remove slow results while JVM is warming up"
  (let [max (max-value field 0.99 ds)]
    ($where {field {:$lt max}} ds)))

(defn scatter-plot-dataset [name]
  (->> (tsv-dataset name)
       (filter-outliers :elapsed-µs)))

; lein run -m miranda.charts.scatter
(defn -main []
  (let [ds (scatter-plot-dataset "docs/sort.tsv")]
    (doto
      (scatter-plot ($ :count ds) ($ :elapsed-µs ds) :group-by ($ :algorithm ds))
      (view))))
