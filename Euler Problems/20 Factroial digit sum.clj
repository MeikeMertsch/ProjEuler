(defn factorials [number]
  (->> (inc number)
       (range 1N)
       (reduce *)))

(factorials 6)
(factorials 100)

(defn split-digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(split-digits 234)

(defn sum-digits [number]
  (->> (split-digits number)
       (reduce +)))

(sum-digits 234)
(sum-digits (factorials 100))