; ---------------------------------------------------- Build maps with lengths

(defn digits []
  {1 (count "one"), 2 (count "two"), 3 (count "three"), 4 (count "four"), 5 (count "five"), 6 (count "six"),
   7 (count "seven"), 8 (count "eight"), 9 (count "nine"), 0 0})

(defn decs-without-ten []
  {2 (count "twenty"), 3 (count "thirty"), 4 (count "forty"), 5 (count "fifty"),
   6 (count "sixty"), 7 (count "seventy"), 8 (count "eighty"), 9 (count "ninety"), 0 0})

(defn teens []
  {10 (count "ten"), 11 (count "eleven"), 12 (count "twelve"), 13 (count "thirteen"), 14 (count "fourteen"),
   15 (count "fifteen"), 16 (count "sixteen"), 17 (count "seventeen"), 18 (count "eighteen"), 19 (count "nineteen")})

; ---------------------------------------------------- Special lengths

(defn length-digit [digit]
  ((digits) digit))

(defn length-teens [numb]
  (-> (+ 10 numb)
      ((teens))
      (#(- % (length-digit numb)))))

(defn length-hundred []
  (count "hundredand"))

(defn length-one-thousand []
  (count "onethousand"))

; ---------------------------------------------------- Transformations

(defn num-to-digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(defn leading-zeros [digs]
  (-> (- 3 (count digs))
      (repeat 0)
      (concat digs)))

; ---------------------------------------------------- Counting

(defn count-hundrets [digs]
  (+ ((digits) (first digs) (- 0 (length-hundred)))
     (if (pos? (first digs)) (length-hundred) 0)
     (if (= (rest digs) '(0 0)) (- 0 3) 0)))

(defn count-teens [digs]
  ((decs-without-ten) (second digs) (length-teens (last digs))))

(defn count-digits [digs]
  ((digits) (last digs)))

(defn count-letters [digs]
  (+ (count-hundrets digs)
     (count-teens digs)
     (count-digits digs)))

(defn count-letters-of [number]
  (->> (num-to-digits number)
       leading-zeros
       count-letters))

; ---------------------------------------------------- Adding counts

(defn add-counts-before [number]
  (->> (range 1 number)
       (map count-letters-of)
       (reduce +)))

(defn counts-until-thousand []
  (+ (add-counts-before 1000) (length-one-thousand)))

(digits)
(decs-without-ten)
(teens)

(length-digit 0)
(length-teens 0)
(length-hundred)
(length-one-thousand)

(num-to-digits 234)
(leading-zeros '(1 2 3))

(count-hundrets '(1 0 0))
(count-teens '(1 0 0))
(count-digits '(1 0 0))
(count-letters '(1 0 0))
(count-letters-of 129)
(count-letters-of 1)
(count-letters-of 2)
(count-letters-of 3)
(count-letters-of 4)
(count-letters-of 5)

(add-counts-before 6)
(counts-until-thousand)
