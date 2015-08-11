(defn is-a-palindrome? [number]
  (=
   (reverse (str number))
   (seq (str number))))

(is-a-palindrome? 9009)
(is-a-palindrome? 9019)

(defn all-palindrome-products []
  (for [n1 (range 999 99 -1)
      n2 (range 999 99 -1)
      :let [product (* n1 n2)]
      :when (is-a-palindrome? product)]
    product))

(all-palindrome-products)

(defn highest-palindrome-product []
  (apply max (all-palindrome-products)))

(highest-palindrome-product)
