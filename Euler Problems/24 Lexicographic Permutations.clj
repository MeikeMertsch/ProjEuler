(defn factorial [number]
  (->> (inc number)
       (range 1)
       (reduce *)))

(defn facs-to-walk []
  (map factorial (range 9 0 -1)))

(factorial 5)
(facs-to-walk)


(defn rounded-down-div [x y]
  (-> (mod x y)
      (->> (- x))
      (/ y)))

(defn determine-next-position [number fac]
  (rounded-down-div number fac))

(rounded-down-div 17 6)
(determine-next-position 1000000 362880)


(defn pull-number [coll pos]
  (->> (nth (last coll) pos)
       (conj (first coll))))

(defn kill-number [coll pos]
  (concat (take pos (last coll))
          (drop (inc pos) (last coll))))

(defn move-number [coll pos]
  (list (pull-number coll pos)
        (kill-number coll pos)))

(pull-number '([] (0 1 2 3 4 5 6 7 8 9)) 5)
(kill-number '([] (0 1 2 3 4 5 6 7 8 9)) 5)
(move-number '([] (0 1 2 3 4 5 6 7 8 9)) 5)

(defn next-number [last-number fac div]
  (- last-number (* fac div)))


(defn my-func [coll fac]
  (->> (determine-next-position (first coll) fac)
       (#(list (next-number (first coll) fac %)
               (move-number (last coll) %)))))

(defn find-nth-lex-permutation-of-coll [n coll]
  (->> (reduce my-func
               [(dec n) (list [] coll)]
               (facs-to-walk))
       last
       flatten
       (apply str)))


(find-nth-lex-permutation-of-coll 1000000 '(0 1 2 3 4 5 6 7 8 9))

(/ 17 (/ 24 4.0))

(/ 1000000 362880.0)
(- 1000000 (* 362880.0 2))

(/ 274240.0 40320)
(- 274240.0 (* 40320 6))

(/ 32320.0 5040)
(- 32320.0 (* 5040 6))

(/ 2080.0 720)
(- 2080.0 (* 720 2))

(/ 640.0 120)
(- 640.0 (* 120 5))

(/ 40.0 24)
(- 40.0 (* 24 1))

(/ 16.0 6)
(- 16.0 (* 6 2))

(/ 4.0 2)
(- 4.0 (* 2 2))




(/ 17.0 6)
(- 17.0 (* 6 2))

(/ 5.0 2)
(- 5.0 (* 2 2))

(/ 1.0 1)
(- 1.0 (* 6 1))

(/ 4.0 2)
(- 4.0 (* 2 2))

