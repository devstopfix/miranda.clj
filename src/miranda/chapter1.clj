; Comments are quotations from the book 
; "Functional Programming with Miranda"
; ©1991 Dr Ian Holyer

(ns miranda.chapter1 "1. Functional Languages")

; 1.2 Declarative Languages
;
; Relationships are declared between values by defining functions.

(defn square 
  "Returns n²"
  [n] 
  (* n n))

; 1.3 Programming with Functions

; 1.3.1 Combining Functions
;
; Functions have no side-effects, no state, and can be combined.

(def fourth-power
  "Returns n⁴ (n raised to the 4th power)"
  (comp square square))

(def squares
  "Lazy seq of the squares of the natural numbers (ℕ - 0,1,2,...)
   generated with map"
   (map square (range)))
