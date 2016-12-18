(ns tamaki.hook
    (:require [me.raynes.fs :as fs]
              [tamaki.page.page :as tpage]
              [tamaki.post.post :as tpost]))

(defn clean [config]
  (fs/delete-dir (:build config)))

(defn initialize [config]
  (fs/mkdirs (:build config)))

(defn process-resources [config]
  (let [res-dir (fs/file (:resources config))]
    (doseq [entity (.listFiles res-dir)]
      (fs/copy entity (:build config)))))

(defn render [config]
  (tpage/compile-pages (:page-dir config)
                       (:site-prefix config)
                       (:build config)
                       (:renderers config))
  (tpost/write-posts (:site-prefix config)
                     (:post-root config)
                     (:build config)
                     (:posts config)
                     (:renderers config)
                     (:pagenate-url config)
                     (:postnum-per-page config)
                     (:pagenate-template config)))

