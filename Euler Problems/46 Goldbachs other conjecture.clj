(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(prime? 21)
(prime? 19)


(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (iterate next-prime 2))

primes


(defn next-composite [number]
  (->> (iterate inc (inc number))
       (drop-while #(or (even? %) (prime? %)))
       first))

(iterate next-composite 1)


(defn can-be-split? [number]
  (not (empty? (for [p (take-while #(< % number) primes)
                     :let [num-to-square (Math/sqrt (/ (- number p) 2))]
                     :when (== num-to-square (int num-to-square))]
                 p))))

(can-be-split? 25)

(time (first (drop-while can-be-split? (rest (iterate next-composite 1)))))