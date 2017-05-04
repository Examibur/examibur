#!/bin/bash
set -e

VOLUMES_DIR=/var/lib/docker/volumes
SERVICE=examibur-dev.service
DATE=$(date +"%Y_%m_%d-%H-%M-%S")
DESTINATION=/opt/backups

VOLUMES_PREFIX='projekteserver_'
VOLUMES=('nginx_dehydrated' 'db_sonarqube' 'sonarqube_data' 'sonarqube_extensions')

mkdir -p ${DESTINATION}
echo "Stopping service $SERVICE"
systemctl stop $SERVICE

for VOLUME in "${VOLUMES[@]}"
do
	echo "Backing up $VOLUME..."
	tar cvzf "${DESTINATION}/examibur-dev-${DATE}-${VOLUME}.tar.gz" "${VOLUMES_DIR}/${VOLUMES_PREFIX}${VOLUME}"
done

echo "Starting service $SERVICE"
systemctl start $SERVICE

