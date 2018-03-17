#!/usr/bin/env bash

set -eo pipefail

modules=( admin-dashboard config-server eureka-server hystrix-dashboard service-a service-b zuul )

for module in "${modules[@]}"; do
    docker build -t "10.11.0.8:5000/spring-cloud-netflix-example-${module}:latest" ${module}
    docker push "10.11.0.8:5000/spring-cloud-netflix-example-${module}:latest"
done
