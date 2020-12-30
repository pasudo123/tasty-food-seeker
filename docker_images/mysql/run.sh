#!/bin/bash

RED_COLOR='\e[31m'
DEFAULT_COLOR='\e[39m'

rootpwd=rootpass
database=tasty-food-seeker
container=tasty-food-seeker-storage
backupPath='/data/backup/db'

usage() {
  echo -e "${RED_COLOR}Please write as below.${DEFAULT_COLOR}"
  echo "./run.sh { build | run | backup | restore }"
}

build() {
  docker build -t tasty-food-seeker-storage .
}

run() {
  docker run -d -p 30666:3306 \
          -e MYSQL_ROOT_PASSWORD=${rootpwd} \
          -e MYSQL_ROOT_HOST=% \
          -e MYSQL_DATABASE=${database} \
          --name tasty_food_seeker_storage \
          ${container}
}

backup() {
  docker exec ${container} /usr/bin/mysqldump -u root --password=${rootpwd} ${database} > ${backupPath}/db_backup.sql
}

restore() {
  cat ${backupPath}/db_backup.sql | docker exec -i ${container} /usr/bin/mysql -u root --password=${rootpwd} ${database}
}


case $1 in
build)
  build
  ;;
run)
  run
  ;;
backup)
  backup
  ;;
restore)
  restore
  ;;
*)
  usage
  ;;
esac