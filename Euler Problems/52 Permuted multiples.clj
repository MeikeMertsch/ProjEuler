(defn str->int [string]
  (->> (str string)
       biginteger
       int))

(str->int "23")

(defn number->digits [number]
  (->> (str number)
       seq
       (map str->int)))

(number->digits 12)

(defn sorted-digits [number]
  (->> (number->digits number)
       sort))

(sorted-digits 87346)

(defn same-digits-in-multiples? [number]
  (->> (for [multiplier (range 1 7)
             :let [multiple (* multiplier number)]]
         (sorted-digits multiple))
       (apply =)
       (vector number)))

(same-digits-in-multiples? 125874)

(time (def find-first-permuted-multiple
  (->> (range)
       (drop 1)
       (map same-digits-in-multiples?)
       (drop-while #(false? (last %)))
       first)))

find-first-permuted-multiple