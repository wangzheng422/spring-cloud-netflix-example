#!/usr/bin/env bash

set -e
set -x

export ONE_BOX=10.11.0.4
export REGION=alauda02
export SPACE_NAME=global
export REGISTRY=10.11.0.15:5000
export ALB_IP=10.11.0.15
export GIT_HOST=10.11.0.5:9988
export NGINX=nginx-10-11-0-15
export AUTH_TOKEN=97898f42b8c95098df3f82037f43bf13fa33ff53

mkdir -p tmp

