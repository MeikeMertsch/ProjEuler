(defn even [number]
  (/ number 2))

(defn odd [number]
  (inc (* number 3)))

(defn treat [number]
  (if (odd? number) (odd number) (even number)))

(defn length-of-chain-with [number]
  (->> (iterate treat number)
       (take-while #(> % 1))
       count))

(defn couple-numbers-and-chain-lengths []
  (->> (range)
       (map #(list % (length-of-chain-with %)))))

(defn one-million []
  (->> (take 1000000 (couple-numbers-and-chain-lengths))
       (sort-by last)))

(treat 5)
(treat 6)
(length-of-chain-with 13)
(couple-numbers-and-chain-lengths)
(time (last (one-million)))
