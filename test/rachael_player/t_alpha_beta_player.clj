(ns rachael-player.t-alpha-beta-player
  (:require [clojure.test :refer :all]
            [rachael-player.alpha-beta-player :refer :all]))

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

(deftest atom-test
  (testing "How do atoms work"
    (def tester 2)
    (def tester 5)
    (is (= tester 5))))

; (deftest score-move-test
;   (testing "Gets the score of a potential move"
;     (is (= (get-score {:board three-moves-for-white-board
;                        :color :white}
;                       43)
;            11))))

; (deftest max-move-test
;   (testing "Does it get the highest scored move"
;     (is (= (get-max-ab-move {:board three-moves-for-white-board
;                           :color :white})
;            43))))
