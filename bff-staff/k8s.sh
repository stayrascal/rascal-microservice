#!/bin/bash

sed -e "s/{IMAGE_VERSION}/"$2"/g" k8s.yml.template | ssh $1 kubectl replace --fore -f -