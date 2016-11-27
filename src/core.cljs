(ns core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)


(def express (node/require "express"))
(def app (express))

(defn -main [& args]
  (. app (get "/" (fn [req res]
                    (. res (send "hello world")))))
   (.log js/console (str "Express server started on port: " (.-port (.address (.listen app 3000))))))


(set! *main-cli-fn* -main)

