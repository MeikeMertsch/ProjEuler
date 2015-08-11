; ----------------------------------- Helper Methods Digits

(defn str-to-int [number]
  (int (biginteger (str 0 number))))

(str-to-int "0234")
(str-to-int "")


(defn to-digits [number]
  (->> (str number)
       seq
       (map str-to-int)))

(to-digits 235)


(defn to-num [digits]
  (->> (apply str digits)
       (str-to-int)))

(to-num '(2 3 4))

; ----------------------------------- Helper Methods Other

(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))

(prime? 21)
(prime? 19)


(defn fact [number]
  (apply * (range 1 (inc number))))

(fact 5)
(fact 9)


(defn high [digits]
  (range digits 0 -1))

(high 9)
(to-num (high 9))


(defn delete-from [x coll]
  (remove #(= x %) coll))

(delete-from 3 '(2 3 1))


;----------------------------------- Construct Next Pandigital Number


(defn pair-up [coll]
  (#(map list (butlast coll) (rest coll))))

(pair-up (reverse (high 9)))


(defn cut-head [coll]
  (->> (pair-up coll)
       (drop-while #(apply > %))
       (map last)
       rest))

(cut-head (reverse (high 9)))
(cut-head '(5 4 6 7 8 9))


(defn cut-tail [coll]
  (->> (reverse coll)
       cut-head
       reverse))

(cut-tail (high 9))


(defn next-lower-num [coll]
  (->> (sort coll)
       (take-while #(not= % (first coll)))
       last
       (#(list % (delete-from % (sort > coll))))
       (delete-from nil)
       flatten))

(next-lower-num '(3 1 2))
(next-lower-num '(1 2))


(defn fill-up [old new]
  (->> (drop (count new) old)
       next-lower-num
       (#(concat new %))))

(fill-up (high 9) '(9 8 7 6 5 4 3))
(fill-up '(9 8 7 6 5 4 3 1 2) '(9 8 7 6 5 4 3))


(defn next-pan [coll]
  (->> (cut-tail coll)
       (fill-up coll)))

(next-pan (high 9))
(next-pan '(9 8 7 6 5 4 3 1 2))
(next-pan '(9 8 7 6 5 4 2 1 3))
(next-pan '(9 8 7 6 5 4 1 2 3))
(next-pan (reverse (high 9)))


(def all-pans-4
  (->> (iterate next-pan (high 4))
       (take (fact 4))
       (map to-num)))

all-pans-4


;----------------------------------- Get The Highest Pandigital Prime Number

(defn prime-pans [coll]
  (->> (iterate next-pan coll)
       (take (fact (count coll)))
       (map to-num)
       (drop-while #(not (prime? %)))))

(time (prime-pans (high 9)))

(drop-while #(not (prime? %)) '(987654321 987654312 987654231))

(time (def all-pans
  (->> (for [x (range 9 0 -1)
             :let [pan (prime-pans (range x 0 -1))]]
         pan)
       flatten)))

all-pans

(time (first all-pans))
