; Comments are quotations from the book 
; "Functional Programming with Miranda"
; ©1991 Dr Ian Holyer

(ns miranda.chapter2 "2. Miranda")

; 2.3.1 Lists and List Comprehension

; Miranda expression where a list is specified 
; in terms of generators and filters
;
;   [n^2 | n<-[1..10]; n mod 2 = 0]
; where
;   | is "such that"
;   <- is ∈ or "taken from"
;
(def squares-of-first-10-even-numbers
  (for [n (range 1 11) :when (even? n)] (* n n)))
