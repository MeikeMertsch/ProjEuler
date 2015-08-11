
(defn number->digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(number->digits 349)

(defn palindromic? [number]
  (->> (number->digits number)
       (#(= % (reverse %)))))

(map palindromic? '(1234 454 384762 2348998432))

(defn digits->number [digits]
  (->> (apply str digits)
       bigint))

(digits->number '(3 4 9))

(defn revert [number]
  (->> (number->digits number)
       reverse
       (digits->number)))

(defn next-step [number]
  (+ number (revert number)))

(next-step 349)

(defn lychrel? [number]
  (->> (iterate next-step number)
       (drop 1)
       (take (inc 50))
       (drop-while #(not (palindromic? %)))
       empty?))

(map lychrel? '(349 196))

(def lychrel-count
  (->> (map lychrel? (range 1 (inc 10000)))
       (replace {true 1, false 0})
       (reduce +)))

lychrel-count