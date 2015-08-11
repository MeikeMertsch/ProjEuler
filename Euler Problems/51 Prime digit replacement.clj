;----- Handle Primes

(defn prime? [number]
  (or (= number 2)
      (and (> number 2)
           (->> (range 2 (inc (Math/sqrt number )))
                (drop-while #(pos? (mod number %)))
                empty?))))

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (take-while #(< % 1000000) (iterate next-prime 11)))

(def candidate-primes
  (drop-while (partial > 56003) primes))

candidate-primes

;----- Conversions

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

(defn digits->number [digits]
  (->> (apply str digits)
       (str->int)))

(digits->number '(1 2 3 5 2 1))


;----- Handling A Number

(defn how-many-occurences-of-012 [number]
  (->> (for [candidate (range 3)]
         (->> (number->digits number)
              (filter (partial = candidate))
              (#(vector candidate (count %)))))
       (filter #(< 1 (last %)))))

(how-many-occurences-of-012 1234000002)


(defn count-primes-in-replacements-for [digits to-be-replaced]
  (->> (for [replacement (range to-be-replaced (inc 9))
             :let [new-number (digits->number (replace {to-be-replaced replacement} digits))]]
         (prime? new-number))
       (replace {true 1, false 0})
       (reduce +)))

(count-primes-in-replacements-for '(5 6 0 0 3) 0)
(count-primes-in-replacements-for '(1 3) 1)
(count-primes-in-replacements-for '(1 2) 1)


(defn figure-replacements-out [number occurences]
  (->> (for [index (range (count occurences))
             :let [to-be-replaced (first (nth occurences index))
                   digits (number->digits number)]]
         (count-primes-in-replacements-for digits to-be-replaced))
       (cons 0)
       (apply max)))

(how-many-occurences-of-012 60101)
(figure-replacements-out 60101 '([0 2] [1 2]))
(figure-replacements-out 60102 '())

(defn find-replacement-prime-count-of [number]
  (->> (how-many-occurences-of-012 number)
       (figure-replacements-out number)
       (vector number)))

(find-replacement-prime-count-of 56003)

(defn find-first-number-with-prime-replacement-count [the-count]
  (ffirst (drop-while #(< (last %) the-count) (map find-replacement-prime-count-of primes))))

(time (find-first-number-with-prime-replacement-count 8))

