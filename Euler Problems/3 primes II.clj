(defn lowest-divisor-of [number]
  (->> (range 2 (inc number))
       (drop-while #(pos? (mod number %)))
       first
  ))

(defn next-number-after [number]
  (->> (lowest-divisor-of number)
       (/ number)
  ))

(defn create-next-coll-with [coll]
  (concat
   (drop-last coll)
   (list (lowest-divisor-of (last coll))
         (next-number-after (last coll)))
   )
  )

(defn building-primes-of [number]
  (->> (list number)
       (iterate #(create-next-coll-with %))
       (take-while #(pos? (dec (last %))))
  ))

(defn primes-of [number]
  (last (building-primes-of number))
  )


(lowest-divisor-of 660)
(next-number-after 660)
(create-next-coll-with '(660))
(building-primes-of 660)
(primes-of 660)
(primes-of 13195)
