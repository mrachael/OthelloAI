(ns rachael-player.t-greedy-player
  (:require [clojure.test :refer :all]
            [rachael-player.greedy-player :refer :all]))

(def one-move-for-white-board {
  :width 8
  :height 8
  :max-index 63
  :squares [
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "w" "-" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "-" "w" "w" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"]})

(def three-moves-for-white-board {
  :width 8
  :height 8
  :max-index 63
  :squares [
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "w" "w" "b" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "-" "-" "w" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"]})

(deftest score-move-test
  (testing "Gets the score of a potential move"
    (is (= (get-score {:board three-moves-for-white-board
                       :color :white}
                      43)
           11))))

(deftest max-move-test
  (testing "Does it get the highest scored move"
    (is (= (get-max-move {:board three-moves-for-white-board
                          :color :white})
           43))))
