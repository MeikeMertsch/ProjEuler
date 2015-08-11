(defn str-to-int [number]
  (int (biginteger (str number))))

(str-to-int "234")


(def constant
  (map str-to-int (seq (apply str (range 500000)))))

constant

(defn digit-n [n]
  (nth constant n))

(digit-n 1)
(digit-n 10)
(digit-n 100)
(digit-n 12)

(def result
  (reduce * (map digit-n '(1 10 100 1000 10000 100000 1000000))))

result