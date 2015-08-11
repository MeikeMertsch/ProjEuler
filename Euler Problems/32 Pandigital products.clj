(defn str-to-int [number]
  (int (bigint number)))

(str-to-int "5")

(defn digits-of [number]
  (->> (str number)
       seq
       (map (comp str-to-int str))))

(digits-of 241)

(defn digits [& numbers]
  (sort (mapcat digits-of numbers)))

(digits 23 75)




(def all-products
  (for [a (range 1 99)
        b (range 123 9877)
        :let [c (* a b)]
        :when (= '(1 2 3 4 5 6 7 8 9) (digits a b c))]
    [a b c]))

(time all-products)

(def find-sum-of-distinct-products
  (->> (map last all-products)
       distinct
       (reduce +)))

find-sum-of-distinct-products