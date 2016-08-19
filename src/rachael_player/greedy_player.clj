(ns rachael-player.greedy-player
  (:require [rachael-player.board :as board]))

(defn get-score
  [game-state move]
  (let [board (:board game-state)
        color (:color game-state)]
    (let [updated-board (board/update-board-for-move board move color)]
      (board/score updated-board color))))

(defn get-max-move
  [game-state]
  (let [moves (board/get-moves game-state)]
    (def get-score-with-state (partial get-score game-state))
    (apply max-key get-score-with-state moves)))

(defn get-move
  [game-state]
  (let [board (:board game-state)
        color (:color game-state)
        moves (board/valid-moves-for-player board color)]
    (get-max-move game-state)))
