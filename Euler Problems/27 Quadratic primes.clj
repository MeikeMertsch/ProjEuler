(defn prime? [number]
  (and (pos? number)
       (->> (range 2 (inc (Math/sqrt number)))
            (drop-while #(pos? (mod number %)))
            empty?)))

(prime? 2)
; :-P

(defn quad [a b number]
  (+ (* number number) (* a number) b))

(defn quadratic [number]
  [number
   (for [a (range -999 1000)
         b (range -999 1000)
         :let [qua (quad a b number)]
         :when (prime? qua)]
     [qua a b])])

(prime? 3)
(prime? -3)
(quadratic 0)


(defn find-next-batch-of-quadratics [[number quadratics]]
  [(inc number)
   (for [qua quadratics
         :let [a (second qua)
               b (peek qua)
               newqua (quad a b (inc number))]
         :when (prime? newqua)]
     [newqua a b])])


(def find-longest
  (first (drop-while #(> (count (last %)) 1) (iterate find-next-batch-of-quadratics (quadratic 0)))))

find-longest

(def prod-coeff
  (* (last (last (last find-longest))) (second (last (last find-longest)))))

prod-coeff
