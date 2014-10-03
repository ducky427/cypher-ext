(ns cypher-ext.core
  (:require [clj-time.format :as tf]
            [clj-time.coerce :as tc]))


(defmulti func (fn [x args] x))

(defmethod func :count
  [_ args]
  (count args))

(defmethod func :str-to-date
  [_ [x fmt]]
  (let [fmt  (or fmt "yyyy-MM-dd")
        ptn  (tf/formatter fmt)
        d    (tf/parse ptn x)]
    (tc/to-long d)))


(defmethod func :date-to-str
  [_ [x fmt]]
  (let [d    (tc/from-long x)
        fmt  (or fmt "yyyy-MM-dd")
        ptn  (tf/formatter fmt)]
    (tf/unparse ptn d)))

(defn call
  [args]
  (func (keyword (first args)) (rest args)))
