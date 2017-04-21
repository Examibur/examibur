#!/bin/sh

curl -L https://github.com/docker/compose/releases/download/1.12.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

/usr/local/bin/docker-compose up --build -d
pwd
ls -l
./run_integration.sh

/usr/local/bin/docker-compose stop

