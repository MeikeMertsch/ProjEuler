(defn x-pow-y [x y]
  (->> (repeat y (bigint x))
       (reduce *)))

(defn str-to-b-int [number]
  (bigint number))

  (defn num-to-digits [number]
    (->> (str number)
         seq
         (map (comp str-to-b-int str))))

(defn sum-of-digits-of-x-pow-y [x y]
  (->> (x-pow-y x y)
       num-to-digits
       (reduce +)))


(x-pow-y 2 100)
(str-to-b-int "325")
(num-to-digits 32768)
(sum-of-digits-of-x-pow-y 2 1000)

; ---------------------------------------------------- Or as one liner:

(reduce + (map (comp bigint str) (seq (str (reduce * (repeat 1000 2N))))))
