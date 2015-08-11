
(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(defn sum-of-primes-with [nprime coll]
  (list nprime
        (+ (last coll) nprime)))

(defn sum-of-primes-of [coll]
  (sum-of-primes-with (next-prime (first coll)) coll))


(prime? 8)
(prime? 5)
(next-prime 7)
(sum-of-primes-of '(5 10))


(time (last (take 10001 (iterate next-prime 2))))
;< 1 sec
(time (last (take-while #(< (first %) 2000000) (iterate sum-of-primes-of '(2 2)))))
;18 secs


'(1999993 142913828922)
