#!/bin/bash

echo "Down - Start ..."

workspace_dir=.
infras=( infra-eureka-server infra-config-server infra-zipkin-server infra-zuul infra-config-client )
services=( service-user service-store service-product service-organization service-order service-address )
bffs=( bff-staff )

for repo_name in ${infras[@]}; do
    pushd ${workspace_dir}/${repo_name}
        if [ -f ./cleanup.sh ]; then
            echo "******* Delete Docker images for: ${repo_name}"
            ./cleanup.sh
        fi
    popd
done


echo "Down - Done."