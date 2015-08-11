(def strings
  (-> (slurp "https://projecteuler.net/project/resources/p042_words.txt")
      (clojure.string/replace #"\"" "")
      (clojure.string/split #",")))

strings


(def char-values
   (zipmap "ABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 1 27)))

char-values


(defn word-value [string]
  (->> (seq string)
       (map char-values)
       (reduce +)))

(word-value "SKY")


(def word-values
  (map word-value strings))

word-values


(def max-value
  (->> (sort word-values)
       last))

max-value


(defn triangle [number]
  (int (* number (inc number) 0.5)))

(triangle 10)


(def triangles
  (->> (map triangle (range))
       (take-while #(<= % max-value))
       rest
       (into #{})))

triangles

(time (count (filter #(not (nil? %)) (map triangles word-values))))

