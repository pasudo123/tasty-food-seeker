#!/bin/bash

RED_COLOR='\e[31m'
DEFAULT_COLOR='\e[39m'
LIGHT_YELLOW_COLOR='\e[93m'

rootpwd=rootpass
database=tasty-food-seeker
imageName=tasty-food-seeker-storage
containerName=tasty_food_seeker_storage
backupPath='/data/backup/db'

usage() {
  echo -e "${RED_COLOR}Please write as below.${DEFAULT_COLOR}"
  echo "./run.sh { build | run | backup | restore }"
}

build() {
  docker build -t ${imageName} .
}

run() {

  # if mysql container running, does not restart.
  containerId=`docker ps -qf name=${containerName}`

  if [ $containerId ]; then
    echo -e "${RED_COLOR}mysql container Running, does not restart.${DEFAULT_COLOR}"
    exit 1
  fi

  containerId=`docker ps -aqf name=${containerName}`

  if [ $containerId ]; then
    echo -e "${LIGHT_YELLOW_COLOR}mysql container Exited, start.${DEFAULT_COLOR}"
    docker start ${containerName}
    exit 0;
  fi

  # be careful !!
  # pre-created user-defined bridge network 'tasty-food-seeker-network'
  echo -e "${LIGHT_YELLOW_COLOR}mysql container Empty, start.${DEFAULT_COLOR}"
  docker run -d -p 30666:3306 \
          -e MYSQL_ROOT_PASSWORD=${rootpwd} \
          -e MYSQL_ROOT_HOST=% \
          -e MYSQL_DATABASE=${database} \
          --name ${containerName} \
          --network tasty-food-seeker-network \
          ${imageName} \
          --character-set-server=utf8mb4 \
          --collation-server=utf8mb4_unicode_ci
}

backup() {
  docker exec ${containerName} /usr/bin/mysqldump -u root --password=${rootpwd} ${database} > ${backupPath}/db_backup.sql
}

restore() {
  cat ${backupPath}/db_backup.sql | docker exec -i ${containerName} /usr/bin/mysql -u root --password=${rootpwd} ${database}
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