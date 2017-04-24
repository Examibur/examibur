#!/bin/bash

curl -L https://github.com/docker/compose/releases/download/1.12.0/docker-compose-Linux-x86_64 > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

/usr/local/bin/docker-compose up --build -d

/bin/bash ./run_integration.sh

/usr/local/bin/docker-compose stop

