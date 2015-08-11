(defn four-times [number]
  (repeat 4 (* 2 (inc number))))

(def jumps
  (flatten (map four-times (range 500))))

(def jumps-small
  (flatten (map four-times (range 2))))

jumps-small
jumps

(defn next-num [number jump]
  (first (drop (+ number jump) (range))))

(next-num 1 2)

(defn find-next-num-and-remaining-jumps [[number the-jumps]]
  [(next-num number (first the-jumps))
   (rest the-jumps)])

(def nums-in-small-diagonals
  (map first (take (inc (count jumps-small)) (iterate find-next-num-and-remaining-jumps [1 jumps-small]))))

(def nums-in-diagonals
  (map first (take (inc (count jumps)) (iterate find-next-num-and-remaining-jumps [1 jumps]))))

nums-in-diagonals
nums-in-small-diagonals
(time (reduce + (map first (take (inc (count jumps-small)) (iterate find-next-num-and-remaining-jumps [1 jumps-small])))))


(def sum-diagonals
  (time (reduce + nums-in-diagonals)))

sum-diagonals

