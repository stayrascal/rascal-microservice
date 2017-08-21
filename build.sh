#!/bin/bash

workspace_dir=.
infras=( infra-eureka-server infra-config-server infra-zipkin-server infra-zuul infra-config-client )
services=( service-user service-store service-product service-organization service-order service-address )
bffs=( bff-staff )


for repo_name in ${infras[@]}; do
    pushd ${workspace_dir}/${repo_name}
        if [ -f ./docker.sh ]; then
            echo "******* Build Docker images for: ${repo_name}"
            ./docker.sh
        fi
    popd
done

for repo_name in ${bffs[@]}; do
    pushd ${workspace_dir}/${repo_name}
        if [ -f ./docker.sh ]; then
            echo "******* Build Docker images for: ${repo_name}"
            ./docker.sh
        fi
    popd
done