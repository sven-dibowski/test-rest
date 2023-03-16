(ns core
  (:require [clojure.data.json :as json]
            [clojure.test :refer [deftest is testing]]
            [hato.client :as hc]))

(defn- send-request []
  (->  (str "https://reqres.in/api/users/")
       hc/get
       :body
       (json/read-str :key-fn keyword)))

(deftest successful-request-test
  (testing "Request is not empty"
    (is (not-empty (send-request) ))
    (is (= 6 (count (send-request)))))) 

((deftest compare-user-test
   (testing "Single User"
     (let [expected {:id 1,
                     :email "george.bluth@reqres.in",
                     :first_name "George",
                     :last_name "Bluth",
                     :avatar "https://reqres.in/img/faces/1-image.jpg"
                     }
           actual (first ((send-request) :data))]
       (is (= expected actual) "returned map should be equals expacted one")))))


