(defn number->digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(number->digits 349)

(defn power [number power]
  (reduce * (repeat power number)))

(power 10N 100)

(defn digitsum [number]
  (->> (number->digits number)
       (reduce +)))

(digitsum (power 10N 100))

(def all-digit-sums
  (for [a (range 1N (inc 100N))
        b (range 1N (inc 100N))
        :let [sum (digitsum (power a b))]]
    sum))

(apply max all-digit-sums)
