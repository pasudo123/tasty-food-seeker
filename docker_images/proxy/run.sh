#!/bin/bash

RED_COLOR='\e[31m'
DEFAULT_COLOR='\e[39m'

imageName=tasty-food-seeker-proxy
containerName=tasty_food_seeker_proxy

usage() {
  echo -e "${RED_COLOR}Please write as below.${DEFAULT_COLOR}"
  echo "./run.sh { build | run }"
}

build() {
  docker build -t ${imageName} .
}

run() {
  docker run -d -p 32333:80 \
          --name ${containerName} \
          --network tasty-food-seeker-network \
          ${imageName}
}

case $1 in
build)
  build
  ;;
run)
  run
  ;;
*)
  usage
  ;;
esac

