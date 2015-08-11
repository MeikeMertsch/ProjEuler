(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (take-while #(< % 1000000) (iterate next-prime 2)))

(reverse primes)

(count primes)


(defn turn [digited-num]
  (conj (butlast digited-num) (last digited-num)))

(turn '(2 3 4))
(turn '(4 2 3))


(defn circle [digited-num]
  (->> (iterate turn digited-num)
       (take (count digited-num))))


(defn str-to-int [number]
  (int (bigint number)))

(str-to-int "5")

(defn to-digits [number]
  (->> (str number)
       seq
       (map (comp str-to-int str))))


(defn to-number [digited-num]
  (->> (apply str digited-num)
       bigint
       int))

(to-number '(2 3 4))

(defn circular-prime? [number]
  (->> (to-digits number)
       circle
       (map to-number)
       (every? prime?)))

(circular-prime? 2)
(circular-prime? 13)
(circular-prime? 17)
(circular-prime? 23)
(circular-prime? 197)


(filter circular-prime? primes)
(time (inc (count (filter circular-prime? primes))))
;inc because my primes? algorithm doesn't recognize 2 as a prime
