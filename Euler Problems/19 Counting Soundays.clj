(defn days-per-month []
  [31 28 31 30 31 30 31 31 30 31 30 31])

(defn days-per-month-ly []
  (replace {28 29} (days-per-month)))

(defn ly-cycle []
  (flatten (concat (repeat 3 (days-per-month)) (days-per-month-ly))))

(defn next-first-sun [last-firsts days-last-month]
  (->> (- 28 days-last-month)
       (+ (last last-firsts))
       (#(if (neg? %) (+ 7 %) %))
       (conj last-firsts)))

(defn jan-1st-1900 []
  (->> (days-per-month)
       (reduce next-first-sun [7])
       last
       vector))

(defn first-suns-per-month [start number-of-months]
  (->> (take number-of-months (cycle (ly-cycle)))
       (reduce next-first-sun start)
       (filter #(= 1 %))
       (reduce +)))


(days-per-month)
(days-per-month-ly)
(cycle (ly-cycle))
(next-first-sun [0] 31)
(jan-1st-1900)
(first-suns-per-month (jan-1st-1900) (* 12 100))

