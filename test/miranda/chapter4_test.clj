(ns miranda.chapter4-test
  (:require [clojure.test :refer :all]
            [miranda.chapter4 :as c4]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(deftest test-pop-empty-queue
  (is (nil? (c4/front (c4/empty-queue)))))

; However many items we add, the first item popped is always first pushed to queue
(defspec test-queue-join
         (prop/for-all [items (gen/vector gen/int) ]
                       (let [q (reduce c4/join (c4/empty-queue) items)]
                         (is (= (first items) (c4/front q))))))

; Add items to a queue, test we get them back in order, and queue is left empty
(defspec test-queue-leave
         (prop/for-all [items (gen/vector gen/int) ]
                       (let [q (reduce c4/join (c4/empty-queue) items)]
                         (loop [q q
                                items items]
                           (if-let [item (first items)]
                             (do
                               (is (= item (c4/front q)))
                               (recur (c4/leave q) (rest items)))
                             (is (nil? (c4/front q))))))))

(deftest test-queue
  (is (= 3 (-> (c4/empty-queue)
               (c4/join 1)
               (c4/join 2)
               (c4/join 3)
               (c4/leave)
               (c4/leave)
               (c4/front)))))
