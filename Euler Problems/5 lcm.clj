; -------------------------------------------- Helper Methods for the Primes

(defn lowest-divisor-of [number]
  (->> (range 2 (inc number))
       (drop-while #(pos? (mod number %)))
       first))

(defn next-number-after [number]
  (->> (lowest-divisor-of number)
       (/ number)))

(defn create-next-coll-with [coll]
  (concat
   (drop-last coll)
   (list (lowest-divisor-of (last coll))
         (next-number-after (last coll)))))

(defn building-primes-of [number]
  (->> (list number)
       (iterate #(create-next-coll-with %))
       (take-while #(pos? (dec (last %))))))

; -------------------------------------------- Methods for solving this problem

(defn primes-of [number]
  (last (building-primes-of number)))

(defn primes-and-their-counts-of [number]
  (->> (primes-of number)
       (group-by #(identity %))
       (#(zipmap
          (sort (keys %))
          (map
           (comp count %)
           (sort (keys %)))))))

(defn map-all-primes-against-count-until [number]
  (map primes-and-their-counts-of (range 2 (inc number))))

(defn all-primes-needed-until [number]
  (->> (map-all-primes-against-count-until number)
       (mapcat keys)
       distinct))

(defn max-no-needed-for-prime [prime number]
  (->> (map #(get % prime 0) (map-all-primes-against-count-until number))
       (apply max)))

(defn pair-primes-with-their-max-count [number]
  (zipmap (all-primes-needed-until number)
                        (map #(max-no-needed-for-prime % number) (all-primes-needed-until number))))

(defn lcm-of-range-up-to [number]
  (->> (mapcat #(repeat (val %) (key %))
                (pair-primes-with-their-count number))
       (reduce *)))

; -------------------------------------------- Steps

(map-primes-against-count-of 20)
(map-all-primes-against-count-until 20)
(all-primes-needed-until 20)
(max-no-needed-for-prime 19 20)
(pair-primes-with-their-max-count 20)
(lcm-of-range-up-to 42)
