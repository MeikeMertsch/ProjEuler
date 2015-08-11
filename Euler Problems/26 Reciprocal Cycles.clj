(defn fraction [number]
  (->> (/ (double 1.0) number)
       str))

(map list (map fraction (range 2 1000)) (range 2 1000))

(mod 10 7)
(mod 30 7)
(mod 20 7)
(mod 60 7)
(mod 40 7)
(mod 50 7)
(mod 10 7)

(mod 10 8)
(mod 20 8)
(mod 40 8)

(mod 10 11)
(mod 100 11)
(mod 10 11)

(mod 10 12)
(mod 100 12)
(mod 40 12)

(mod 10 14)
(mod 100 14)
(mod 20 14)
(mod 60 14)
(mod 40 14)
(mod 120 14)
(mod 80 14)
(mod 100 14)




(defn next-num [[last-nums div]]
  (-> (* 10 (peek last-nums))
      (mod div)
      (->> (conj last-nums))
      (vector div)))

(iterate next-num [[1] 7])


(defn with-duplicate? [[coll _]]
  (contains? (into #{} (butlast coll)) (last coll)))

(with-duplicate? [[1 4 3 2] 0])


(defn divide-until-duplication-by [number]
  (ffirst (drop-while #(not (with-duplicate? %)) (rest (iterate next-num [[1] number])))))

(divide-until-duplication-by 7)
(divide-until-duplication-by 4)


(defn count-cycle-length-with [coll]
  (if (= 0 (last coll)) 0 (count (drop-while #(not= (last coll) %) (butlast coll)))))


(count-cycle-length-with [1 3 2 6 4 5 1])
(count-cycle-length-with [1 2 0 0])


(defn cycle-length-of [number]
  (count-cycle-length-with (divide-until-duplication-by number)))

(cycle-length-of 7)


(def max-cycle-length
  (apply max (map cycle-length-of (range 1 (inc 1000)))))

max-cycle-length

(defn find-longest-cycle []
  (last (first (drop-while #(not= max-cycle-length (first %)) (reverse (map #(list (cycle-length-of %) %) (range 1 (inc 1000))))))))

(time (find-longest-cycle))

