(defn x-divides-y? [x y]
  (->> (mod y x)
       pos?
       not))

(defn count-divisors [number]
  (->> (Math/sqrt number)
       inc
       (range 1)
       (filter #(x-divides-y? % number))
       count
       (* 2)))

(defn triangulars []
  (reductions + (range)))

(defn combine-with-divisor-count [tri]
  (list tri (count-divisors tri)))

(defn map-all-divisor-counts []
  (map combine-with-divisor-count (triangulars)))

(defn first-500-div-tri []
  (first (drop-while #(< (last %) 500) (map-all-divisor-counts))))


(x-divides-y? 5 10)
(x-divides-y? 5 12)
(count-divisors 28)
(triangulars)
(combine-with-divisor-count 15)
(map-all-divisor-counts)
(time (first-500-div-tri))