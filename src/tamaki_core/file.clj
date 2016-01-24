(ns tamaki-core.file
  (:require [clojure.java.io :as io]
            [tamaki-core.config :as config]))


(defn create-empty-file [maybe-unexists-filename]
  "Create an empty file to be written afterwards."
  (let [file (io/file maybe-unexists-filename)]
    (.mkdirs (.getParentFile file))
    (.createNewFile file)))

(defn clean-dest
  "Make the destination empty."
  ([] (clean-dest (-> (config/read-config) :dest io/file)))
  ([dest] (doseq [file (.listFiles dest)] (.delete file))))