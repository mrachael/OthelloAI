(ns rachael-player.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string]
            [rachael-player.board :as board]
            [rachael-player.player :as player])
  (:gen-class))

(defn file-exists? [file-path]
  (.exists (clojure.java.io/as-file file-path)))

(defn contains-keys? [m & ks]
  (every? true? (map #(contains? m %) ks)))

(defn exec-and-wait [millis f & args]
  (let [result (apply f args)]
    (when (pos? millis)
      (Thread/sleep millis))
    result))

(defmacro time-limited [ms & body]
  `(let [f# (future ~@body)]
     (.get f# ~ms java.util.concurrent.TimeUnit/MILLISECONDS)))

(def cli-options
  [["-b" "--board BOARD" "JSON reprresentation of the board."
    :validate [board/parse-board-string "Could not parse the given board."]]
   ["-p" "--player PLAYER" "The player to use (white or black)."
    :validate [board/parse-player-string "Invalid player symbol (must be white or black)."]]
   ["-t" "--time MILLIS" "The maximum time allowed for a move."]
   ["-h" "--help"]])

(defn invalid-options? [options]
  (not (contains-keys? options :board :player)))

(defn usage [options-summary]
  (->> ["This application is an AI for an Othello game. It picks a valid move at random."
        ""
        "Usage: -b BOARD -p PLAYER"
        "Where BOARD is a JSON reprresentation of the board and the player specifies \"white\" or \"black\""
        "Options:"
        options-summary
        ""]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn invalid-options-msg [summary]
  (println "Sorry, some required options were missing.")
  (newline)
  (usage summary))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn get-move [board color]
   (player/get-move {:player-type :greedy
                     :board board
                     :color color}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
   (let [{:keys [options errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (usage summary))
      errors (exit 1 (error-msg errors))
      (invalid-options? options) (exit 1 (invalid-options-msg summary)))
    (System/exit (get-move (board/parse-board-string (:board options))
                           (board/parse-player-string (:player options))))))
