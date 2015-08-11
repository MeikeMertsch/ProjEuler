(defn fibonaccies []
  (->> (iterate #(list (last %) (reduce + %)) '(0 1N))
       (map last)))

(fibonaccies)

(defn count-digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))
       count))

(count-digits 3423356N)

(defn combine-fib-counts-with-idx []
  (map #(list (count-digits %) %2)
       (fibonaccies)
       (range 1 5000)))

(combine-fib-counts-with-idx)

(defn find-first-1000-digs []
  (->> (combine-fib-counts-with-idx)
       (drop-while #(< (first %) 1000))
       first))

(time (find-first-1000-digs))
