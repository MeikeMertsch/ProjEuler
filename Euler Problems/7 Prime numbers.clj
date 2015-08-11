(defn lowest-divisor-of [number]
  (->> (range 2 (inc number))
       (drop-while #(pos? (mod number %)))
       first))

(defn nth-prime-number [number]
  (->> (filter #(= % (lowest-divisor-of %)) (range))
       (take number)
       last))

(prime-number 10001)



(distinct (map #(lowest-divisor-of %) (range 2 2000000)))
(nth (distinct (map #(lowest-divisor-of %) (range 2 2000000))) (dec 10001))