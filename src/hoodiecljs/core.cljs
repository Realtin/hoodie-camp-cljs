(ns hoodiecljs.core
    (:require [reagent.core :as r]))

;; -------------------------
;; State

(def online-status (r/atom {:online false}))

;; -------------------------
;; Views

(defn check-connection []
  (.connectionStatus.startChecking js/hoodie {:interval 1000}))

(defn online-check []
  [:div
    [:h2 "Check Connection"]
    [:p "I am online: " (str @online-status) ". "
      [:input {:type "button" :value "Check Connection"
               :on-click #(swap! online-status assoc :online js/hoodie.connectionStatus.ok)}]]])

(defn online-component []
  (r/create-class {:reagent-render online-check
                   :component-did-mount check-connection}))

(defn sorting-and-creating-data []
  [:div
    [:h2 "Storing and finding data"]])

(defn storing-creating-sorting-component []
  (r/create-class {:reagent-render sorting-and-creating-data}))

(defn home-page []
  [:div [:h2 "Welcome to Hoodie"]
    [online-component]
    [storing-creating-sorting-component]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
