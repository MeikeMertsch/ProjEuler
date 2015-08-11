(defn divide-rounded-down [x y]
  (->> (mod x y)
       (- x)
       (#(/ % y))))

(divide-rounded-down 50 20)


(defn count-of-next-available-coin [[value coins coll]]
  [(mod value (first coins))
   (rest coins)
   (conj coll (divide-rounded-down value (first coins)))])

(count-of-next-available-coin [50 '(20 10 5 2 1) []])
(count-of-next-available-coin [8 '(1) []])

(defn find-tail [value coins]
  (->> (iterate count-of-next-available-coin [value coins []])
       (take (inc (count coins)))
       last
       last
       reverse))

(find-tail 50 '(20 10 5 2 1))
(find-tail 8 '(1))

(defn value-of [coll]
  (reduce + (map * (reverse coll)
                 '(200 100 50 20 10 5 2 1))))

(value-of '(3 2 1))


(defn fix-end [coll]
  (find-tail (- 200 (value-of coll)) (drop (count coll) '(200 100 50 20 10 5 2 1))))

(fix-end '(6 0 1 1 1 1 0))

(defn tail [coll]
  (flatten (list (dec (first coll))
                 (rest coll))))

(tail '(7 0 1 1 1 1 0))

(defn assemble [coll]
  (->> (list (fix-end (tail coll))
             (tail coll))
       flatten))

(defn next-number [sequence]
  (->> (rest sequence)
       (drop-while #(= 0 %))
       assemble))

(next-number '(6 7 0 1 1 1 1 0))
(next-number '(0 0 0 0 0 0 0 1))

(def all-combinations
  (inc (count (take-while #(not= % '(200 0 0 0 0 0 0 0)) (iterate next-number '(0 0 0 0 0 0 0 1))))))

(time all-combinations)