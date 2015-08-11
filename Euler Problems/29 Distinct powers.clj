(defn function []
  (for [a (range 2N (inc 100))
        b (range 2N (inc 100))
        :let [pot (reduce * (repeat b a))]]
    pot))

(count (distinct (function)))