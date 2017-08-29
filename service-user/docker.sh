#!/bin/bash

cd $(dirname $0)
DIR=$(pwd)

./gradlew clean build

export JAR_FILE=$(cd build/libs && ls *.jar)
cd $DIR

docker build -f ./docker/Dockerfile -t stayrascal/service-user:latest --build-arg jar=$JAR_FILE .