;----- Prime Handling

(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (iterate next-prime 3))

primes

;----- Pairing Numbers And Checking Them

(defn str->int [string]
  (bigint string))

(defn connect [n m]
  (->> (str n m)
       (str->int)))

(connect 234 439857)

(defn all-primes? [primes-set]
  (->> (for [x primes-set
             y primes-set
             :let [number (connect x y)]
             :when (not= x y)]
         number)
       (every? prime?)))

(all-primes? #{2 3 5})
(all-primes? #{3 7 109})

;----- Checking All Combinations

(defn check [number]
  (for [a (take-while (partial > number) primes)
             :let [numberset (hash-set a number)]
             :when (all-primes? numberset)]
    [a number]))

(time (first (drop-while empty? (apply concat (map check primes)))))
