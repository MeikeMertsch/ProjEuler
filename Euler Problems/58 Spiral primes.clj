(defn prime? [number]
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?))



(def steps
  (range 2 100000 2))

steps

(defn times-4 [number]
  (repeat 4 number))

(times-4 5)

(def all-steps
  (->> (map times-4 steps)
       flatten))

all-steps

(def diagonals
  (reductions + 1 all-steps))

diagonals

(def squares
  (partition 4 (rest diagonals)))

squares

(defn primes-in-square [square]
  (->> (map prime? square)
       (filter true?)
       count))

(primes-in-square '(3 5 7 9))

(def primes-per-square
  (reductions + (cons 0 (map primes-in-square squares))))

primes-per-square

(def diagonal-numbers
  (reductions + 1 (repeat 4)))

diagonal-numbers

(def ratios
  (drop 3 (map / primes-per-square diagonal-numbers)))

ratios

(+ 7 (* 2 (count (take-while (partial <= 1/10) ratios))))
