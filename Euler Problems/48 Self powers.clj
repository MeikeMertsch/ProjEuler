(defn self-power [number]
  (reduce * (repeat number number)))

(def series
  (reduce + (map self-power (range 1N 1001N))))

series

(def last-ten-digits
  (->> (str series)
       (#(subs % (- (count %) 10)))
       bigint))

last-ten-digits