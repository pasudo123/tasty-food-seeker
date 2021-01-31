#!/bin/bash
# shellcheck disable=SC2012

RED_COLOR='\e[31m'
CYAN_COLOR='\e[96m'
GREEN_COLOR='\e[32m'
DEFAULT_COLOR='\e[39m'

#buildDirectory="build/libs/"
#appName="tasty-food-seeker-crawl"

#cd ../../

#echo -e "\n===> ${GREEN_COLOR}build jar file${DEFAULT_COLOR}"
#buildJarFile="./gradlew :${appName}:bootJar"
#$buildJarFile

#cd ${appName} || exit

#echo -e "\n===> ${GREEN_COLOR}latest jar file${DEFAULT_COLOR}"
#fetchLatestJarFile=`ls -t "$buildDirectory" | head -1`
#latestJar=$fetchLatestJarFile
#echo -e "\n===> latest jar :: ${RED_COLOR}${latestJar}${DEFAULT_COLOR}"

# using docker-compose.yml
#P_LATEST_JAR=''
#P_YEAR=2020
#P_MONTH=11
#echo -e "\n===> ${CYAN_COLOR}using docker-compose container${DEFAULT_COLOR}"
#YEAR=${P_YEAR} MONTH=${P_MONTH} docker-compose up -d --build
