;--------------------------------- Create Primes

(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (iterate next-prime 2))

;--------------------------------- Highest Prime Sum With Specific Start

(defn candidates [lazy-primes-nailing-start maximum]
  (let [cands lazy-primes-nailing-start]
    (->> (reductions + cands)
         (take-while (partial > maximum)))))

(defn highest-prime-sum [lazy-primes-nailing-start]
  (->> (candidates lazy-primes-nailing-start 1000000)
       reverse
       (drop-while #(not (prime? %)))
       first))

(highest-prime-sum primes)

;--------------------------------- The Real Deal

(apply max (for [offset (range 10)
                 :let [offset-primes (drop offset primes)]]
             (highest-prime-sum offset-primes)))


