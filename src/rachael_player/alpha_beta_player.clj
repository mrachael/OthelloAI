(ns rachael-player.alpha-beta-player
  (:require [rachael-player.board :as board]
            [rachael-player.greedy-player :as greedy]))

(declare get-score)

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

(defn alpha-beta
  [options]
  (let [depth (:depth options)
        a (:alpha options)
        b (:beta options)
        max-color (:max-color options)
        game-state (:game-state options)
        board (:board game-state)
        color (:color game-state)]
    (def v 0)
    (def alpha a)
    (def beta b)
    (if (or (= depth 0) (= (board/get-moves game-state) 0))
      (board/score board max-color)
      (do
        (let [moves (board/get-moves game-state)]
          (if (= (:color game-state) max-color)
            (do
              (def v (- (:max-index board)))
              (loop [iter 0]
                (let [move (nth moves iter)
                      next-state ({:board (board/update-board-for-move board move color)
                                   :color (board/opponent max-color)})]
                  (def v (max v (alpha-beta {:game-state next-state
                                               :depth (- depth 1)
                                               :alpha alpha
                                               :beta beta
                                               :max-color max-color})))
                  (def alpha (max alpha v))
                  (if (and (< iter (count moves)) (not (<= beta alpha)))
                    (recur (inc iter)))))
              v)
            (do
              (def v (:max-index board))
              (loop [iter 0]
                (let [move (nth moves iter)
                      next-state ({:board (board/update-board-for-move board move color)
                                   :color max-color})]
                  (def v (min v (alpha-beta {:game-state ()
                                                :depth (- depth 1)
                                                :alpha alpha
                                                :beta beta
                                                :max-color max-color})))
                  (def beta (min beta v))
                  (if (and (< iter (count moves)) (not (<= beta alpha)))
                    (recur (inc iter)))))
              v)))))
    (v)))

(defn get-score
  [game-state move]
  (let [current-board (:board game-state)
        max-color (:color game-state)]
    (def board-after-move (board/update-board-for-move current-board move max-color))
    (def start-state {:board board-after-move
                      :color (board/opponent max-color)})
    (def ab-opts {:game-state start-state
                  :depth 3
                  :alpha (- (:max-index board-after-move))
                  :beta (:max-index board-after-move)
                  :max-color max-color})
    (alpha-beta ab-opts)))

