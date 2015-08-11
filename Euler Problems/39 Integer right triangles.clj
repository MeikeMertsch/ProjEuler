(defn perimeter [a b]
  (+ a b (Math/sqrt (+ (* a a)(* b b)))))

(perimeter 20 48)


(defn all-combs [p]
  (for [a (range 1 p)
        b (range 1 p)
        :while (<= b a)
        :when (== p (perimeter a b))]
    1))

(count (all-combs 120))


(defn count-combs [number]
  [(count (all-combs number)) number])

(count-combs 120)

(time (last (sort-by first (map count-combs (range 1 1001)))))
