;;; 1

(reduce + (filter #(contains? (set [(mod % 3) (mod % 5)]) 0) (range 1000)))

(filter #(contains? (set [(mod % 3) (mod % 5)]) 0) (range 1000))

;;; 2


(reduce + (map last (filter #(even? (last %)) (take-while #(< (last %) 4000000) (iterate #(list (last %) (reduce + %)) '(1 1))))))


;;; 3

((fn [n] (filter #(= 0 (mod n %)) (range (dec n) 2 -1))) 13195 )
((fn [n] (filter #(= 0 (mod n %)) (range (dec n) 2 -1))) 600851475143  )


((fn [n] [(first (drop-while #(not= 0 (mod n %)) (range 2 n)))]) 600851475143)


