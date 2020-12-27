#!/bin/bash
# shellcheck disable=SC2012

RED_COLOR='\e[31m'
CYAN_COLOR='\e[96m'
GREEN_COLOR='\e[32m'
DEFAULT_COLOR='\e[39m'

buildDirectory="build/libs/"
appName="tasty-food-seeker-crawl"

cd ../../

echo -e "\n===> ${GREEN_COLOR}build${DEFAULT_COLOR} jar file"
buildJarFile="./gradlew :${appName}:bootJar"
$buildJarFile

cd ${appName} || exit

echo -e "\n===> ${GREEN_COLOR}fetch${DEFAULT_COLOR} latest jar file"
fetchLatestJarFile=`ls -t "$buildDirectory" | head -1`
latestJar=$fetchLatestJarFile
echo -e "\n===> latest jar :: ${RED_COLOR}${latestJar}${DEFAULT_COLOR}"

# using docker-compose.yml
# jar 파일은 하나만 있어야 한다.
echo -e "\n===> ${CYAN_COLOR}using docker-compose${DEFAULT_COLOR} container"
LATEST_JAR="$latestJar" YEAR="2020" MONTH="11" docker-compose up -d --build