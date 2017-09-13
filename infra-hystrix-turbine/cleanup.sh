#!/bin/bash

image_name="infra-hystrix-turbine"

docker ps | grep -E "\s${image_name}\s" | awk '{print $1}' | xargs -I {} docker kill {}

docker ps -a | grep -E "\s${image_name}\s" | awk '{print $1}' | xargs -I {} docker rm -f {}

docker images | grep -E "\s${image_name}\s" | awk '{print $3}'| xargs -I {} docker rmi {}