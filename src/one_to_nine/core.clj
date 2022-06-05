(ns one-to-nine.core
  (:require [clojure.math.combinatorics :as combo]))

(defn all-possible-boards
  "What are all the possible combinations of the set of numbers from 1 to x."
  [x]
  (combo/permutations (range 1 (inc x))))

(defn horiz-vertical-coords
  "Index for array for the rows and columns of a board with width * height squares"
  [width height]
  (let [rows (for [row (range 0 height)] (range (* row width) (* (inc row) width)))
        columns (for [col (range 0 width)] (range col (* width height) width))]
    (concat rows columns)))

(defn results-from-board
  [operation width height board]
  (let [values-to-operate-on (map #(map (partial nth board) %) (horiz-vertical-coords width height))]
    (map (partial reduce operation) values-to-operate-on)))

(defn board-from-results
  "For boards width * height what are the possible combinations so that the results of operations
  on rows and columns are `results`."
  [operation width height results]
  (let [boards (all-possible-boards (* width height))]
    (filter #(= (results-from-board operation width height %) results) boards)))

(defn -main[ & args]
  (println(board-from-results + 3 3 '(12 14 19 10 16 19))))