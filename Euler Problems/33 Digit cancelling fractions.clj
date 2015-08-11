(defn build-num [f g]
  (+ (* 10 f) g))

(build-num 2 3)

(defn fraction [x y a]
  (/ (build-num x a)
     (build-num a y)))

(fraction 4 8 9)
(= 49/98 1/2)


(def all-curious-fractions
  (for [x (range 1 (inc 9))
        y (range 1 (inc 9))
        a (range 1 (inc 9))
        :when (== (fraction x y a) (/ x y))]
    [(fraction x y a)]))

all-curious-fractions

(denominator (reduce * (flatten all-curious-fractions)))