;----- Basics

(defn value [card]
  ((zipmap "23456789TJQKA"
           '(2 3 4 5 6 7 8 9 10 11 12 13 14))
   (first card)))

(value "KC")

(defn suit [card]
  (second card))

(suit "KC")

(defn sort-my [hand]
  (->> (map value hand)
       (partition-by identity)
       (sort-by count >)
       (partition-by count)
       (map #(sort-by first > %))
       flatten
       (partition-by identity)))

(sort-my '("2D" "2D" "4D" "4D" "7D"))
(sort-my '("4D" "4D" "2D" "2D" "7D"))


;----- Specific Patterns

(defn flush? [hand]
  (->> (map suit hand)
       (apply =)))

(flush? '("2D" "3D" "4D" "5D" "7D"))
(flush? '("2C" "3D" "4D" "5D" "7D"))

(defn straight? [hand]
  (->> (map value hand)
       (#(and (apply < %)
              (= (+ 4 (first %)) (last %))))))

(straight? '("2D" "3D" "4D" "5D" "7D"))
(straight? '("2D" "3D" "4D" "5D" "6D"))

(defn four-of-a-kind? [sorted-hand]
  (= 4 (count (first sorted-hand))))

(map four-of-a-kind? '( ((2 2 2 2) (11)) ((2 2 2) (11 11))))


(defn full-house? [sorted-hand]
  (= '(3 2) (map count sorted-hand)))

(map full-house? '( ((2 2 2 2) (11)) ((2 2 2) (11 11)) ((2 2 2) (11) (12))))


(defn three-of-a-kind? [sorted-hand]
  (= 3 (count (first sorted-hand))))

(map three-of-a-kind? '( ((2 2 2 2) (11)) ((2 2 2) (11) (10))))

(defn two-pairs? [sorted-hand]
  (= '(2 2 1) (map count sorted-hand)))

(defn one-pair? [sorted-hand]
  (= '(2 1 1 1) (map count sorted-hand)))


;----- Values Of Patterns

(def pattern->rank
  {:straight-flush 9
   :four-of-a-kind 8
   :full-house 7
   :flush 6
   :straight 5
   :three-of-a-kind 4
   :two-pairs 3
   :one-pair 2
   :high-card 1})


(defn pattern-of-multiple [hand]
  (let [sorted-hand (sort-my hand)]
    (if (four-of-a-kind? sorted-hand)
      :four-of-a-kind
      (if (full-house? sorted-hand)
        :full-house
        (if (three-of-a-kind? sorted-hand)
          :three-of-a-kind
          (if (two-pairs? sorted-hand)
            :two-pairs
            (if (one-pair? sorted-hand)
              :one-pair
              :high-card)))))))

(defn pattern-of [hand]
  (if (flush? hand)
    (if (straight? hand)
      :straight-flush
      :flush)
    (if (straight? hand)
      :straight
      (pattern-of-multiple hand))))


(map pattern-of '(("2H" "3D" "4C" "5D" "6S")
                  ("2H" "3D" "4C" "5D" "7S")
                  ("2H" "2D" "4C" "5D" "6S")
                  ("2H" "3D" "3C" "3D" "5S")
                  ("2H" "3D" "3C" "3D" "3S")))


;----- Value Of Any Pattern

(defn value-of [sorted-hand]
  (->> (for [index (range (count sorted-hand))
             :let [value (first (nth sorted-hand index))
                   divisor (apply * (repeat (* 2 (inc index)) 10))]]
         (bigdec (/ value divisor)))
       (reduce +)))


(map value-of '(((5 5 5 5 5))
                ((2 2 2) (13 13))
                ((13 13) (10) (3) (2))))



;----- Determine The Rank Of A Hand

(defn rank-of [hand]
  (+ (pattern->rank (pattern-of hand))
     (value-of (sort-my hand))))

(map rank-of '(("2H" "3D" "4C" "5D" "6S")
                  ("2H" "3D" "4C" "5D" "7S")
                  ("2H" "2D" "4C" "5D" "6S")
                  ("2H" "3D" "3C" "3D" "5S")
                  ("2H" "3D" "3C" "3D" "3S")))


(pattern-of '("5C" "5D" "9C" "AD" "AC"))
(rank-of '("5H" "7C" "8D" "TD" "KS"))
(sort-my '("5C" "5D" "9C" "AD" "AC"))
;----- Take Care Of That File

(defn sort-hand [unsorted-hand]
  (sort-by value unsorted-hand))


(defn handline->hands [handline]
  (->> (clojure.string/split handline #"\s+")
       (partition 5)
       (map sort-hand)))

(handline->hands "8C TS KC 9H 4S 7D 2S 5D 3S AC")

(def handpairs
  (->> (slurp "https://projecteuler.net/project/resources/p054_poker.txt")
       (clojure.string/split-lines)
       (map handline->hands)))

handpairs
(count handpairs)

(defn player1-winner [handpair]
  (->> (map rank-of handpair)
       (apply >)))


(->> (map player1-winner handpairs)
     (replace {true 1, false 0})
     (reduce +))
