(defn triangle? [number]
  (-> (* 8 number)
      inc
      Math/sqrt
      dec
      (rem 2)
      (== 0)))

(triangle? 10)
(triangle? 12)
(triangle? 15)


(defn pentagon? [number]
  (-> (* 24 number)
      inc
      Math/sqrt
      inc
      (rem 6)
      (== 0)))

(pentagon? 1)
(pentagon? 2)
(pentagon? 5)


(defn hex [number]
  (* number (- (* 2 number) 1)))

(map hex (range 6))


(def large-hexagonal-numbers
  (drop-while #(<= % 40755) (map hex (range))))

large-hexagonal-numbers

(first (filter #(and (triangle? %)
                     (pentagon? %))
               large-hexagonal-numbers))