(def pentagon-numbers
  (->> (map #(int (* % (- (* 3 %) 1) 0.5)) (range))
       rest
       (take 10000)))

pentagon-numbers


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
(pentagon? 21)
(pentagon? 22)
(pentagon? 48)
(pentagon? 92)

(defn valid-pair? [j k]
  (and (pentagon? (+ j k))
       (pentagon? (- k j))))

(valid-pair? 1 5)
(valid-pair? 22 70)

;this is a little bit of a cheat as I guessed how many pentagon-numbers I'll need.
(reduce - (first (for [x pentagon-numbers
      y pentagon-numbers
      :when (< x y)
      :let [valid (valid-pair? x y)]
      :when (true? valid)]
  [x y])))