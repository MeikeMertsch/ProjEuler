(defn square [number]
  (* number number))

(defn sum-of-squares-up-to [number]
  (->> (inc number)
       range
       (map square)
       (reduce +)))

(defn square-of-sum-up-to [number]
  (->> (inc number)
       range
       (reduce +)
       square))

(defn sum-square-difference [number]
  (- (square-of-sum-up-to number)
     (sum-of-squares-up-to number)))


(square 4)
(sum-of-squares-up-to 10)
(square-of-sum-up-to 10)
(sum-square-difference 10)
