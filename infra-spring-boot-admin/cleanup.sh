#!/bin/bash

image_name="infra-spring-boot-admin"

docker ps | grep -E "${image_name}" | awk '{print $1}' | xargs -I {} docker kill {}

docker ps -a | grep -E "${image_name}" | awk '{print $1}' | xargs -I {} docker rm -f {}

docker images | grep -E "${image_name}" | awk '{print $3}'| xargs -I {} docker rmi {}