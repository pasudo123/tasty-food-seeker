#!/bin/bash

CYAN_COLOR='\e[96m'
GREEN_COLOR='\e[32m'
DEFAULT_COLOR='\e[39m'

buildDirectory="build/libs/*"
appName="tasty-food-seeker-crawl"

cd ../

#echo -e "\n===> ${GREEN_COLOR}remove${DEFAULT_COLOR} jar files"
#removeJarFiles="rm -r ${buildDirectory}"
#$removeJarFiles
#
#cd ../
#
#echo -e "\n===> ${GREEN_COLOR}build${DEFAULT_COLOR} jar file"
#buildJarFile="./gradlew :${appName}:bootJar"
#$buildJarFile
#
#cd ${appName} || exit

echo -e "\n===> ${GREEN_COLOR}fetch${DEFAULT_COLOR} latest jar file"
fetchLatestJarFile="ls -al ${buildDirectory}"
$fetchLatestJarFile

## using Dockerfile
#echo -e "\n===> ${CYAN_COLOR}build dockerFile${DEFAULT_COLOR}"
#dockerBuildCommand="docker build -t tasty-food-seeker-crawl:latest ."
#$dockerBuildCommand

# using docker-compose.yml
echo -e "\n===> ${CYAN_COLOR}using docker-compose.yml${DEFAULT_COLOR}"
dockerComposeCommand="docker-compose up -d --build"
$dockerComposeCommand