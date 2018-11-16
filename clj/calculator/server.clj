(ns calculator.server
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [calculator.polynomial :as polynomial]))

(defroutes app-routes
  (GET "/differentiate/*" [*]
    (try
      {:status 200
       :body (-> *
               (polynomial/parse)
               (polynomial/differentiate)
               (polynomial/stringify))}
      (catch Exception e
        {:status 400
         :body (str "Invalid input: " *)})))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (jetty/run-jetty app {:port 3000}))
