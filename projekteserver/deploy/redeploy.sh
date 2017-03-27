#!/bin/bash
set -e

WATCH_DIR=/home/deploy
WATCH_FILE=${WATCH_DIR}/deploy
SERVICES=db_examibur staging
DOCKER_COMPOSE_FILE=/opt/examibur/projekteserver/docker-compose.yml

rm ${WATCH_FILE} || true

while true;
do

  # Wait on a change on the watchfile
  if [ "$(inotifywait -e create ${WATCH_DIR})" == "/home/deploy/ CREATE deploy" ]; then

    echo "File ${WATCH_FILE} was created!"
    while rm ${WATCH_FILE} 2>/dev/null; do 
      echo "Checking changes..."
      cd /opt/examibur/
      git fetch
      if [ "$(git diff origin/master)" != "" ]; then
          echo "Performing re-deploy..."
          git pull origin master
          docker-compose -f "${DOCKER_COMPOSE_FILE}" stop ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" kill ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" rm ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" up --remove-orphans --force-recreate ${SERVICES}
      fi
      echo "done!"
    done
  fi
done
