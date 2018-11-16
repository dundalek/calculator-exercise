
# Calculator exercise

Differentiation of polynomials

## Clojure

Source code is in the `clj` directory.

Run the server with (listens on port 3000):
```sh
clojure -m calculator.server
```

Try it out with:
```sh
curl localhost:3000/differentiate/3/2/1
# => 6x+2
```

With the server running run the tests with:
```
clj -Atest
```


## Ruby

Source code is in the `rb` directory.

Install dependencies (tested on ruby 2.3):
```
bundle install
```

Run the server with (listens on port 4567):
```
cd rb
ruby server.rb
```

Try it out with:
```sh
curl localhost:4567/differentiate/3/2/1
# => 6x+2
```

With the server running run the tests with:
```
HOST="http://localhost:4567" clj -Atest
```
