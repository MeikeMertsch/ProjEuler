(defn str-to-b-int [number]
  (int (bigint number)))


(defn num-to-digits [number]
    (->> (str number)
         seq
         (map (comp str-to-b-int str))))

(defn fourth-power [number]
  (reduce * (repeat 4 number)))

(defn fifth-power [number]
  (reduce * (repeat 5 number)))

(fourth-power 6)

(defn sum-of-fourth-powers [digits]
  (reduce + (map fourth-power digits)))

(defn sum-of-fifth-powers [digits]
  (reduce + (map fifth-power digits)))

(map num-to-digits (range 10 20))
(sum-of-fourth-powers '(1 6 3 4))

(for [n (range 100000)
      :when (= n (sum-of-fourth-powers (num-to-digits n)))]
  n)

(dec (reduce + (for [n (range 1000000)
      :when (= n (sum-of-fifth-powers (num-to-digits n)))]
  n)))