(ns calculator.polynomial
  (:require [clojure.string]))

(defn monomial-stringify [coefficient exponent]
  (if (zero? coefficient)
    ""
    (str coefficient
         (case exponent
             0 ""
             1 "x"
             (str "x^" exponent)))))

(defn parse [s]
  (->>
    (clojure.string/split s #"/")
    (map #(Integer/parseInt %))))

(defn degree [p]
  (dec (count p)))

(defn stringify [p]
  (let [degree (degree p)]
    (->> p
      (map-indexed
        (fn [idx coefficient]
          (let [s (monomial-stringify coefficient (- degree idx))]
            (if (and (pos? idx) (not (clojure.string/blank? s)) (pos? coefficient))
              (str "+" s)
              s))))
      (clojure.string/join ""))))

(defn differentiate [p]
  (let [degree (degree p)]
    (->> p
      (drop-last)
      (map-indexed (fn [idx coefficient]
                     (* coefficient (- degree idx)))))))

(comment
  (parse "3/2/1")

  (monomial-stringify 3 2)
  (monomial-stringify 0 2)

  (stringify [3 2 1])

  (stringify (differentiate [1 0 0]))

  (stringify (differentiate [3 2 1]))

  (stringify (differentiate (parse "-4/-5/0/1"))))
