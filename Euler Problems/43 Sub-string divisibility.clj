; ----------------------------------- Helper Methods Digits

(defn str-to-int [number]
  (biginteger (str 0 number)))

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
(delete-from 3 '(1 3 3))


(defn func [[a-coll coll]]
  [(rest a-coll)
   (delete-from (first a-coll) coll)])


(defn delete-coll-from [a-coll coll]
  (last (first (drop (count a-coll) (iterate func [a-coll coll])))))

(delete-coll-from '(2 1 3) '(1 2 3 4 5 6 7 8 1 2 3 4 3 2 1))


(defn dividable? [number divisor]
  (= 0 (rem number divisor)))

(dividable? 10 5)
(dividable? 10 3)


;----------------------------------- Construct Pandigital Numbers

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

(defn count-less-when-0-involved [coll]
  (- (fact (count coll))
     (if (= 0 (first (sort coll)))
       (fact (dec (count coll)))
       0)))

(count-less-when-0-involved '(1 2 3 4))
(count-less-when-0-involved '(1 2 0 4))


(defn pans-of [coll]
  (->> (iterate next-pan coll)
       (take (count-less-when-0-involved coll))))

(time (pans-of (high 9)))

;----------------------------------- Get The 17-Multiples That Matter

(def multiples-17
  (->> (map (partial * 17) (range))
       (drop-while #(< % 100))
       (take-while #(< % 1000))
       (map to-digits)))

multiples-17


(defn matters? [coll]
  (and (= 3 (count (distinct coll)))
       (<= 2 (count (delete-from 5 (delete-from 0 coll))))))

(matters? '(5 1 0))
(matters? '(5 1 2))
(matters? '(3 1 2))
(matters? '(1 1 2))


(def valid-17-multies
  (filter matters? multiples-17))

valid-17-multies


;----------------------------------- Get All The Needed Reduced Pandigital Numbers

(defn get-first-seven-digits [coll]
  (delete-coll-from coll (range 9 -1 -1)))

(get-first-seven-digits '(1 0 2))


(defn not-in [x coll]
  (not (contains? (into #{} coll) x)))

(not-in 4 '(1 2 4))
(not-in 4 '(1 2 3))


(count (map #(concat % '(1 2 3)) (pans-of (delete-from 3 (get-first-seven-digits '(1 2 3))))))


(defn generate-pans-from-last-three-digits [x coll]
  (map #(concat % coll) (pans-of (delete-from x (get-first-seven-digits coll)))))

(generate-pans-from-last-three-digits 3 '(1 2 0))


(time (def all-shortened-pans
  (->> (for [x (range 10)
             multy valid-17-multies
             :when (not-in x multy)
             :let [link (generate-pans-from-last-three-digits x multy)]]
         link)
       (apply concat))))

all-shortened-pans

(time (count all-shortened-pans))


;----------------------------------- Check All For Validity

(defn valid? [coll]
  (->> (partition 3 1 coll)
       (#(and (dividable? (to-num (nth % 0)) 2)
              (dividable? (to-num (nth % 1)) 3)
              (dividable? (to-num (nth % 2)) 5)
              (dividable? (to-num (nth % 3)) 7)
              (dividable? (to-num (nth % 4)) 11)
              (dividable? (to-num (nth % 5)) 13)))))

(valid? '(4 0 6 3 5 7 2 8 9))
(valid? '(4 0 6 3 5 7 2 9 8))

(defn preceed-with-x [coll]
  (concat (delete-coll-from coll (range 10)) coll))

(preceed-with-x '(1 2 3 4 5 6 7 8 9))


(def sum-over-all-valid-pans
  (time (reduce + (map (comp to-num preceed-with-x) (filter valid? all-shortened-pans)))))

sum-over-all-valid-pans