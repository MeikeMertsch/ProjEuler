(defn factorials [number]
  (if (== 0 number)
    1
    (reduce * (range 1N (inc number)))))

(map factorials (range 7))

(defn nCr [n r]
  (/ (factorials n)
     (* (factorials r)
        (factorials (- n r)))))

(nCr 5 3)
(nCr 23 10)

(time (def combinatoric-selections
  (for [n (range 1 (inc 100))
        r (range 1 (inc 100))
        :let [ncr (nCr n r)]
        :when (<= r n)
        :when (< 1000000 ncr)]
    1)))

(count combinatoric-selections)
