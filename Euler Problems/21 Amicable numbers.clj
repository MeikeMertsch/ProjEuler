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
       inc
       (range 1)
       (filter #(x-divides-y? % number))
       (mirror-divisors number)))

(divisors 220)
(divisors 9)
(divisors 0)

(defn sum-of-divisors [number]
  (reduce + (divisors number)))

(sum-of-divisors 220)
(sum-of-divisors 284)

(defn find-all-amicable-numbers []
  (for [x (range 1 (inc 10000))
        :let [sumdiv (sum-of-divisors x)]
        :when (and (= (sum-of-divisors sumdiv) x)
                   (not= sumdiv x))]
    x))

(find-all-amicable-numbers)

(time (reduce + (find-all-amicable-numbers)))

