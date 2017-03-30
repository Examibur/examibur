#!/bin/bash
set -e

WATCH_DIR="/home/deploy/"
WATCH_FILE="deploy"
SERVICES="examibur"
DOCKER_COMPOSE_FILE=/opt/examibur/projekteserver/docker-compose.yml

rm "${WATCH_DIR}${WATCH_FILE}" || true

while true;
do

  # Wait on a change on the watchfile
  RESULT=$(inotifywait -e create ${WATCH_DIR})
  if [ "${RESULT}" == "${WATCH_DIR} CREATE ${WATCH_FILE}" ]; then

    echo "File ${WATCH_FILE} was created!"
    while rm "${WATCH_DIR}/${WATCH_FILE}" 2>/dev/null; do
      echo "Checking changes..."
      cd /opt/examibur/
      git fetch
      if [ "$(git diff origin/master)" != "" ]; then
          echo "Performing re-deploy..."
          git pull origin master
          systemctl daemon-reload # If a systemd service has changed
          docker-compose -f "${DOCKER_COMPOSE_FILE}" stop ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" kill ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" rm -f ${SERVICES}
          docker-compose -f "${DOCKER_COMPOSE_FILE}" up -d --remove-orphans --force-recreate --build ${SERVICES}
      fi
      echo "done!"
    done
  fi
done

