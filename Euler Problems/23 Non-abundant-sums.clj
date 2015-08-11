(defn x-divides-y? [x y]
  (->> (mod y x)
       pos?
       not))

(x-divides-y? 5 15)
(x-divides-y? 5 16)

(defn mirror-divisors [number coll]
  (->> (map #(/ number %) (rest coll))
       (concat coll)
       distinct))

(mirror-divisors 220 '(1 2 4 5 10 11))
(mirror-divisors 9 '(1 3))

(defn divisors [number]
  (->> (Math/sqrt number)
       (#(if (< (inc %) number) (inc %) %))
       (range 1)
       (filter #(x-divides-y? % number))
       (mirror-divisors number)))

(divisors 220)
(divisors 9)
(divisors 2)

(defn sum-of-divisors [number]
  (reduce + (divisors number)))

(sum-of-divisors 220)
(sum-of-divisors 284)
(sum-of-divisors 2)

(defn abundant? [number]
  (> (sum-of-divisors number) number))

(abundant? 2)
(abundant? 12)

(defn find-all-abundant-numbers-to [number]
  (for [x (range 1 (inc number))
        :let [sumdiv (sum-of-divisors x)]
        :when (> sumdiv x)]
    x))

(find-all-abundant-numbers-to 28124)
(count (find-all-abundant-numbers-to 28124))
(last (find-all-abundant-numbers-to 28124))

(defn find-all-abundant-sums [abus]
  (for [x abus
        y abus
        :let [sum (+ x y)]
        :when (and (<= x y) (<= sum 28124))]
    sum))

(defn sum-of-all-abundand-sums-to [number]
  (->> (find-all-abundant-numbers-to number)
       find-all-abundant-sums
       distinct
       (reduce +)))

(sum-of-all-abundand-sums-to 28124)

(defn sum-all-numbers-to [number]
  (-> (inc number)
      (* number)
      (/ 2)))

(sum-all-numbers-to 6)

(defn sum-all-non-sums-to [number]
  (- (sum-all-numbers-to number)
     (sum-of-all-abundand-sums-to number)))

(time (sum-all-non-sums-to 28124))