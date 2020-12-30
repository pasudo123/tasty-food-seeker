#!/bin/bash

docker build -t tasty-food-seeker-storage .

docker run -d -p 30666:3306 \
-e MYSQL_ROOT_PASSWORD=rootpass \
-e MYSQL_ROOT_HOST=% \
-e MYSQL_DATABASE=tasty-food-seeker \
--name tasty_food_seeker_storage \
tasty-food-seeker-storage


