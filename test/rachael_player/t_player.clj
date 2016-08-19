(ns rachael-player.t-player
  (:require [clojure.test :refer :all]
            [rachael-player.player :refer :all]
            [rachael-player.board :as board]))

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

(def two-moves-for-white-board {
  :width 8
  :height 8
  :max-index 63
  :squares [
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "w" "-" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "-" "w" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"]})

(def no-moves-for-white-board {
  :width 8
  :height 8
  :max-index 63
  :squares [
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "w" "w" "-" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "w" "b" "b" "w" "-" "-"
    "-" "-" "-" "w" "w" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"
    "-" "-" "-" "-" "-" "-" "-" "-"]})

(deftest happy-day-move
  (testing "Finds a move in the happy day case"
    (is (= (get-move {:board one-move-for-white-board
                      :color :white})
           19))))

(deftest sad-day-move
  (testing "Returns -1 in a sad universe"
    (is (= (get-move {:board no-moves-for-white-board
                      :color :white})
            -1))))

(deftest different-players
  (testing "Uses the type of player given"
    (is (not= (get-move {:board two-moves-for-white-board
                         :color :white})
              (get-move {:player-type :second-player
                         :board two-moves-for-white-board
                         :color :white})))))

(deftest get-moves-test
  (testing "Does it get all of the moves for this game state"
    (is (= (board/get-moves {:board two-moves-for-white-board
                       :color :white})
           [19 44]))))
