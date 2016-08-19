(ns rachael-player.player
  (:require [rachael-player.board :refer :all]
            [rachael-player.greedy-player :as greedy]
            [rachael-player.alpha-beta-player :as alpha-beta]))

(defmulti get-move (fn [game-state] (:player-type game-state)))

(defmethod get-move :default
  [game-state]
  (let [board (:board game-state)
        color (:color game-state)]
    (if (any-valid-moves-for-player? board color)
      (let [moves (valid-moves-for-player board color)]
          (first moves))
      -1)))

(defmethod get-move :second-player
  [game-state]
  (let [board (:board game-state)
        color (:color game-state)]
    (if (any-valid-moves-for-player? board color)
      (let [moves (valid-moves-for-player board color)]
          (second moves))
      -1)))

(defmethod get-move :greedy
  [game-state]
  (greedy/get-move game-state))

(defmethod get-move :alpha-beta
  [game-state]
  (alpha-beta/get-move game-state))


; Implement a simple AB player here; this will be more complex than any of my cellmates anyway
; ToDo: How does clj handle interfaces?

; 6pm be able to swap out players by interface CHECK!
; 8pm have an AB player up in hur
; 8pm Start poking at threading?
