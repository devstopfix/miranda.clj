; Comments are quotations from the book
; "Functional Programming with Miranda"
; ©1991 Dr Ian Holyer

(ns miranda.chapter4 "4. The Design of Modules")

; 4.3 Queues

;(defprotocol Queue
;  "First In First Out (FIFO) Queue"
;  (emptyqueue [] "Return the empty queue")
;  (join [q x] "Return new queue with x added to q")
;  (front [q] "Return the next item from the queue")
;  (leave [q] "Return a queue with the front item removed"))

; Easiest implementation of a queue uses a single list.
; However there is an efficency problem for long queues,
; the join function takes an amount of time proportional
; to the length of the queue.

; Represent a queue as a pair of lists - a list of items
; at the front of the queue and a list of items at
; the back of the queue.
(defn empty-queue
  "Create a new empty queue."
  []
  [ '() '() ])

; Define the invariant that every non-empty queue has a non-empty front list,
; then the front function always takes constant time.
(defn front
  "Return the next item to be popped from the queue
  (or nil if the queue is empty)"
  [q]
  (ffirst q))

; For an empty queue the item is put in the front list,
; otherwise the item is put at the beginning of the back list.
(defn join
  "Return a new queue, with item x added"
  [q x]
  (let [[front back] q]
    (if (empty? front)
      [ (list x) back]
      [ front (cons x back)])))

; The leave function removes the first item from the front of the list,
; if this is the only item then the back list must be reversed and moved
; to the front list. Leave takes constant time on average.
(defn leave
  "Return queue with first item removed"
  [q]
  (let [[front back] q]
    (if (empty? (rest front))
      [(reverse back) '()]
      [(rest front) back])))
