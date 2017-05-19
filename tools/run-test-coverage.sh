#!/bin/bash

set -e

REPO_BASE=$( cd "$( dirname "$0" )/.." && pwd )

JACOCO_TOMCAT=$REPO_BASE/examibur/jacoco/jacocoTomcat.exec
if [ -f "$JACOCO_TOMCAT" ]; then
    rm -f $JACOCO_TOMCAT
fi

# create jacoco file with user as owner
touch $JACOCO_TOMCAT 

cd $REPO_BASE/examibur/
./gradlew clean assemble

docker-compose stop
docker-compose rm -f
docker-compose up -d --build

export LOG_FILE="examibur.log"
./gradlew test

docker build -t examibur/ui_tests -f ../docker/Dockerfile.integration ../docker/
/bin/bash $REPO_BASE/tools/run-integration.sh

docker-compose stop
docker-compose rm -f
./gradlew jacocoTestReport
echo "Jacoco Report: file://$REPO_BASE/examibur/build/reports/jacoco/html/index.html"
