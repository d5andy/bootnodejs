(set-env!
 :source-paths #{"src"}
 :resource-paths #{ "resources" }
 :dependencies '[
                 [adzerk/boot-cljs "1.7.228-1" :scope "test"]
                 [adzerk/boot-cljs-repl   "0.3.0"]
                 [org.clojure/clojurescript   "1.7.228"]
		[ajchemist/boot-figwheel "0.5.2-2"]
[org.clojure/tools.nrepl "0.2.12" :scope "test"]
[com.cemerick/piggieback "0.2.1" :scope "test"]
[figwheel-sidecar "0.5.3-2" :scope "test"]
                ])

(require 
         '[adzerk.boot-cljs :refer :all])

(deftask build[] 
 (cljs 
   :compiler-options {:target :nodejs :pretty-print true}
   :optimizations :simple
   :source-map true))

(require 'boot-figwheel)
(refer 'boot-figwheel :rename '{cljs-repl fw-cljs-repl})
(task-options!
figwheel {:build-ids  ["dev"]
           :all-builds [{:id "dev"
                         :compiler {:main 'core
                                    :output-to "main.js"
			            :target :nodejs}
                         :figwheel {
                                    }}]
           :figwheel-options { }} 
)
(deftask dev []
  (set-env! :source-paths #(into % ["src"]))
  (comp (repl) (figwheel)))

