(defn str-to-int [number]
  (int (bigint number)))

(str-to-int "5")

(defn digits-of [number]
  (->> (str number)
       seq
       (map (comp str-to-int str))))

(digits-of 241)

(defn factorial [number]
  (reduce * (range 1 (inc number))))

(factorial 6)

(defn digit-factorials [number]
  (->> (digits-of number)
       (map factorial)
       (reduce +)))

(digit-factorials 145)

(reduce + (for [x (range 3 100000)
      :when (= x (digit-factorials x))]
  x))

;this is a bit cheated as I guess the upper limit. But I could also go ahead and go for all possible combinations of the factorials of 0 through 9 to find the numbers