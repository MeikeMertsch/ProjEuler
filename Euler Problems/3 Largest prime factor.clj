(defn lowest-divisor-of [number]
  (->> (range 2 (inc number))
       (drop-while #(pos? (mod number %)))
       first
  ))

(defn next-number-after [number]
  (->> (lowest-divisor-of number)
       (/ number)
  ))

(defn create-next-vector-with [a-vector]
  [(cons (lowest-divisor-of (peek a-vector)) (first a-vector))
   (next-number-after (peek a-vector))]
  )

(defn building-primes-of [number]
  (->> ['() number]
       (iterate #(create-next-vector-with %))
       (take-while #(pos? (dec (last %))))
  ))

(defn primes-of [number]
  (->> (building-primes-of number)
       last
       flatten
       sort
  ))

(lowest-divisor-of 30)
(next-number-after 30)
(building-primes-of 660)
(primes-of 13195 )