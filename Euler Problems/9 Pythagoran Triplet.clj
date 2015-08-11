(defn square [number]
  (* number number))

(defn triplets-for-sum [number]
  (for [a (range 1 number)
        b (range 2 number) :when (< a b)
        c (range 3 number) :when (< b c)
        :let [sum (+ (square a)
                    (square b))]
        :when (and (= sum (square c)) (= number (+ a b c)))]
    [a b c sum (square c)]))

(defn product-a-b-c []
  (->> (triplets-for-sum 1000)
       flatten
       butlast
       butlast
       (reduce *)))

(triplets-for-sum 1000)
(product-a-b-c)