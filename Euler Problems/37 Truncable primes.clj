(defn prime? [number]
  (or (= number 2)
      (and (> number 2)
           (->> (range 2 (inc (Math/sqrt number )))
                (drop-while #(pos? (mod number %)))
                empty?))))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (take-while #(< % 1000000) (iterate next-prime 11)))

primes

(defn str-to-int [number]
  (int (bigint number)))

(str-to-int "5")

(defn to-digits [number]
  (->> (str number)
       seq
       (map (comp str-to-int str))))

(defn truncate-right [number]
  (take (count number) (iterate rest number)))

(defn truncate-left [number]
  (take (count number) (iterate butlast number)))

(truncate-right '(3 7 9 7))
(truncate-left '(3 7 9 7))

(defn to-number [digited-num]
  (->> (apply str digited-num)
       bigint
       int))

(to-number '(2 3 4))

(defn truncs-of [digits]
  (->> (concat (truncate-left digits)
               (truncate-right digits))
       distinct))

(defn all-truncs-of [number]
  (->> (to-digits number)
       truncs-of
       (map to-number)))

(all-truncs-of 3797)
(all-truncs-of 11)

(every? prime? '(11 1))
(prime? 1)
(prime? 2)
(prime? 3)
(prime? 4)


(time (reduce + (for [p primes
      :when (every? prime? (all-truncs-of p))]
  p)))