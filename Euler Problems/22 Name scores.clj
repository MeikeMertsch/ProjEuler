(defn names []
  (-> (slurp "https://projecteuler.net/project/resources/p022_names.txt")
      (clojure.string/replace #"\"" "")
      (clojure.string/split #",")
      sort))

(names)
(count (names))

(defn value-mapping []
  (zipmap "ABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 1 (inc 26))))

(value-mapping)

(defn name-to-value [a-name]
  (->> (seq a-name)
       (map #((value-mapping) % 0))
       (reduce +)))

(name-to-value "COLIN")

(defn all-values []
  (map #(* %2 (name-to-value %))
       (names)
       (rest (range))))

(time (reduce + (all-values)))