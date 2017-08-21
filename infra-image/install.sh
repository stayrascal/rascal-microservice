#!/bin/bash

mkdir /apps/java8
tar -xzf /apps/docker/java8.tar.gz -C /apps/java8 --strip 1
rm -f /apps/docker/java8.tar.gz

mkdir /apps/filebeat
tar -xzf /apps/docker/filebeat.tar.gz -C /apps/filebeat --strip 1
rm -f /apps/docker/filebeat.tar.gz