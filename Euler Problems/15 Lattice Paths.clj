(defn factorials [number]
  (->> (biginteger number)
       inc
       (range 1N)
       (reduce *)))

(defn combinations-x-over-y [x y]
  (/ (factorials x)
     (* (factorials y)
        (factorials (- x y)))))

(factorials 40)
(factorials 20)
(* (factorials 20) (factorials 20))
(time (combinations-x-over-y 40 20))
