(ns one-to-nine.core-test
  (:require [clojure.test :refer :all]
            [one-to-nine.core :refer :all]))

(deftest all-possible-boards-test
  (are [x boards] (= (all-possible-boards x) boards)
                  2 '([1 2] [2 1])
                  3 '([1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1])))

(deftest horiz-vertical-coords-test
  (are [width height coords] (= (horiz-vertical-coords width height) coords)
                             2 2 '([0 1] [2 3] [0 2] [1 3])
                             3 3 '([0 1 2] [3 4 5] [6 7 8]
                                   [0 3 6] [1 4 7] [2 5 8])))



(deftest results-from-board-test
  (is (= (results-from-board + 3 3
         '(8 4 9
            5 3 6
            2 7 1))
         '(21 14 10 15 14 16)
         )))


(deftest board-from-results-test
  (is (= (board-from-results + 3 3
                             '(21 14 10 15 14 16))

         '(8 4 9
            5 3 6
            2 7 1))))
