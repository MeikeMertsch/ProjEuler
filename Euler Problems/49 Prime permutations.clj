(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (->> (iterate next-prime 2)
       (drop-while #(< % 1000))
       (take-while #(< % 10000))))

primes

(defn to-num [string]
  (int (bigint (str string))))

(defn to-digits [number]
  (->> (str number)
       seq
       (map to-num)))


(def pair-primes-with-config
  (map list primes (map #(sort (to-digits %)) primes)))

pair-primes-with-config


(def permutating-primes
  (->> (group-by last pair-primes-with-config)
       vals
       (map #(map first %))
       (filter #(>= (count %) 3))
       (sort-by first)))

permutating-primes


(defn concat-valid-numbers [coll]
  (for [a coll
        b coll
        c coll
        :when (and (= (- b a) (- c b)) (< a b c))]
    (str a b c)))

(concat-valid-numbers '(1487 1847 4817 4871 7481 7841 8147 8741))

(flatten (map concat-valid-numbers permutating-primes))









