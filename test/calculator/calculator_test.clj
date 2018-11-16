(ns calculator.calculator-test
  (:require [clojure.test :refer [deftest is are]]
            [clj-http.client :as http]))

(def host (or (System/getenv "HOST") "http://localhost:3000"))

(deftest differential-examples
  (are [x y] (= y (:body (http/get (str host "/differentiate" x))))

    "/3/2/1"
    "6x+2"

    "/4/3/2/1"
    "12x^2+6x+2"

    "/4/3/0/1"
    "12x^2+6x"

    "/4/-5/0/1"
    "12x^2-10x"

    "/-2/0/0"
    "-4x"))

(deftest differential-invalid
  (is (= 404 (:status (http/get (str host "/invalid-path") {:throw-exceptions false}))))

  (is (= 400 (:status (http/get (str host "/differentiate/invalid/number") {:throw-exceptions false})))))
