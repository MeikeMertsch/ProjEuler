(defn times-2 [number]
  (* 2 number))

(def powers-of-2
  (iterate times-2 1N))

powers-of-2

(defn needed-bins [number]
  (reverse (take-while #(<= % number) powers-of-2)))

(needed-bins 56)

(defn next-digit [[[number coll] bins]]
  [(if (>= number (first bins))
     [(- number (first bins))
      (conj coll true)]
     [number
      (conj coll false)])
   (rest bins)])

(next-digit [[56 []] '(32 16 8 4 2 1)])

(defn do-more [number bins]
  (take (inc (count bins)) (iterate next-digit [[number []] bins])))


(defn to-binary [number]
  (->> (needed-bins number)
       (do-more number)
       last
       first
       last
       (replace {true 1 false 0})
))

(to-binary 585)
(to-binary 4)

(defn str-to-int [number]
  (int (bigint number)))

(str-to-int "5")

(defn digits-of [number]
  (->> (str number)
       seq
       (map (comp str-to-int str))))

(digits-of 241)

(defn palindrome? [coll]
  (= coll (reverse coll)))

(palindrome? [4 3 4])

(def all-palindromes
  (for [x (range 1000000)
        :when (and (palindrome? (digits-of x)) (palindrome? (to-binary x)))]
    x))

(time all-palindromes)

(time (reduce + all-palindromes))



