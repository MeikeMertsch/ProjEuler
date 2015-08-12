;----- Prime Handling

(defn prime? [number]
  (if (not= 1 number)
  (->> (range 2 (inc (Math/sqrt number )))
       (drop-while #(pos? (mod number %)))
       empty?)))

(prime? 1)

(defn next-prime [number]
  (->> (iterate inc (inc number))
       (drop-while #(not (prime? %)))
       first))

(def primes
  (->> (iterate next-prime 3)
       (take-while (partial > 100000))))


primes
(count primes)

;----- Find All Pairs

(defn number->digits [number]
  (->> (str number)
       seq
       (map (comp int bigint str))))

(number->digits 349)

(defn cut [number]
  (->> (number->digits number)
       (#(for [length (range 1 (+ 1/2 (/ (count %) 2)))
               :let [parts (split-at length %)]]
           parts))))

(cut 345678)
(cut 45678)

(defn digits->number [digits]
  (->> (apply str digits)
       bigint
       int))

(digits->number '(3 4 5 6 7) )

(defn cuts->numbers [cuts]
  (map (partial map digits->number) cuts))

(cuts->numbers '([(3) (4 5 6 7 8)] [(3 4) (5 6 7 8)] [(3 4 5) (6 7 8)]))

(defn rule-out [pairs]
  (filter (partial every? prime?) pairs))

(rule-out '((3 45678) (34 5678) (345 678) (3 7) (3 2)))

(defn number->pairs [number]
  (->> (cut number)
       cuts->numbers
       rule-out
       (map reverse)
       rule-out))

(def pairs
  (->> (apply concat (map number->pairs primes))
       (map sort)
       distinct
       (sort-by last)))

pairs
(time (count pairs))


;----- Find Triples From Pairs

(partition-by last pairs)

(def triple-candidates
  (filter #(< 1 (count %)) (partition-by last pairs)))

triple-candidates

(count triple-candidates)


(def number->smaller-partners
  (zipmap (map #(last (last %)) triple-candidates)
          (map #(into #{} (map first %)) triple-candidates)))

number->smaller-partners
(number->smaller-partners 11)

(contains? (into #{} pairs) '(3 5))


(defn real-triples [number a-set]
  (for [a a-set
        b a-set
        :when (< a b)
        :let [pair (list a b)]
        :when (contains? (into #{} pairs) pair)]
    #{number a b}))

(real-triples 109 #{3 7 21})

(def triples
  (->> (for [a (keys number->smaller-partners)
             :let [triple (real-triples a (number->smaller-partners a))]]
         triple)
       (apply concat)))

(count triples)
triples

;----- Find Quadruples From Triples

(def all-quadruple-candidates
  (->> (for [a triples
             b triples
             :when (not= a b)
             :let [candidate (clojure.set/union a b)]
             :when (= 4 (count candidate))
             :when (clojure.set/difference a b)]
         candidate)
       distinct))

(count all-quadruple-candidates)


;----- Pairing Numbers And Checking Them

(defn str->int [string]
  (bigint string))

(defn connect [n m]
  (->> (str n m)
       (str->int)))

(connect 234 439857)

(defn all-primes? [primes-set]
  (->> (for [x primes-set
             y primes-set
             :let [number (connect x y)]
             :when (not= x y)]
         number)
       (every? prime?)))

(all-primes? #{2 3 5})
(all-primes? #{3 7 109})

;----- Checking All Combinations

(defn check [number]
  (for [a (take-while (partial > number) primes)
             :let [numberset (hash-set a number)]
             :when (all-primes? numberset)]
    [a number]))

(time (first (drop-while empty? (apply concat (map check primes)))))
(time (count (apply concat (map check primes))))
