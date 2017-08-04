#!/bin/bash

#/apps/filebeat/filebeat -c /apps/boot/config/filebeat.yml &

$JAVA_HOME/bin/java -jar /apps/boot/$JAR_FILE -Xmx512M -XX:MaxPermSize:256M