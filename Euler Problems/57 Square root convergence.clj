(defn num->digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(map num->digits '(123 34269 3))


(defn next-number [last-number]
  (+ 2 (/ 1 last-number)))

(map next-number '(2.5 2.4))

(def rootconvergence
  (map dec (iterate next-number 5/2)))

rootconvergence

(defn numerator-exceeds-denom? [fraction]
  (> (count (num->digits (numerator fraction)))
     (count (num->digits (denominator fraction)))))

(count (filter (partial = true) (take 1000 (map numerator-exceeds-denom? rootconvergence))))

