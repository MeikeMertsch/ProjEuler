(defn str-to-int [number]
  (int (biginteger (str number))))

(str-to-int "32")

(defn digits-of [number]
  (->> (str number)
       seq
       (map str-to-int)))

(digits-of 324)

(defn multies [number limit]
  (map #(digits-of (* number %)) (range 1 limit)))

(multies 3 10)

(defn row-of-multies [number]
  (for [x (range 2 11)
        :let [m (multies number x)]]
    m))

(row-of-multies 3)

(defn biggest-pandigital [number]
  (->> (take-while #(apply distinct? (flatten %)) (row-of-multies number))
       last
       flatten))

(biggest-pandigital 3)

(def all-count-nine-pandas
  (for [x (range 1 10000)
        :let [p (biggest-pandigital x)]
        :when (= 9 (count p))]
    p))

all-count-nine-pandas



(filter #(not (contains? (into #{} %) 0)) all-count-nine-pandas)
(apply max (map #(str-to-int (apply str %)) (filter #(not (contains? (into #{} %) 0)) all-count-nine-pandas)))
