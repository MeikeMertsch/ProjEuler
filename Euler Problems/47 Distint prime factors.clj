;------------------------------------ Finding The Prime Factors Of A Number

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

primes


(defn lowest-divisor-of [number]
  (->> (drop-while #(pos? (mod number %)) primes)
       first))

(lowest-divisor-of 644)


(defn create-next-coll-with [number coll]
  (concat coll (list (lowest-divisor-of (/ number (reduce * coll))))))

(defn primes-of [number]
  (->> (iterate #(create-next-coll-with number %) '())
       (drop-while #(not= number (reduce * %)))
       first))

(primes-of 644)

;------------------------------------ Find Four Valid Consecutive Integers

(defn has-four-dist-primes? [number]
  (->> (primes-of number)
       distinct
       count
       (== 4)))

(has-four-dist-primes? 644)
(has-four-dist-primes? (* 2 3 5 7))


(defn next-valid-number [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (has-four-dist-primes? %)))
       first))

(def all-four-dist-primes
  (rest (iterate next-valid-number 1)))

all-four-dist-primes

(def get-first-and-last-from-possible-quadruple
  (map list all-four-dist-primes
       (drop 3 all-four-dist-primes)))

get-first-and-last-from-possible-quadruple

(defn not-valid? [coll]
  (not= 3 (- (last coll) (first coll))))


(time (def first-valid-starter
  (->> (drop-while not-valid? get-first-and-last-from-possible-quadruple)
       ffirst)))

(time first-valid-starter)