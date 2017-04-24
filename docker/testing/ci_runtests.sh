#!/bin/bash

docker-compose up --build -d

/bin/bash /builds/engineering-projekt/examibur/run-integration.sh

docker-compose stop

