#!/bin/sh

curl -L https://github.com/docker/compose/releases/download/1.12.0/docker-compose-`uname -s`-`uname -m` > docker-compose
chmod +x docker-compose

./docker-compose up --build -d
pwd
/bin/sh run_integration.sh

./docker-compose stop

