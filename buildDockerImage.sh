#!/usr/bin/env bash

set -eo pipefail

source config.sh

modules=( admin-dashboard config-server eureka-server hystrix-dashboard service-a service-b zuul )

for module in "${modules[@]}"; do
    docker build -t "${REGISTRY}/spring-cloud-netflix-example-${module}:latest" ${module}
    docker push "${REGISTRY}/spring-cloud-netflix-example-${module}:latest"
done
