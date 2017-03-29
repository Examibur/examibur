#!/bin/bash
set -e

VOLUMES_DIR=/var/lib/docker/volumes/
SERVICE=examibur-dev.service
DATE=$(date +"%Y_%m_%d-%H-%M-%S")
DESTINATION=/opt/backups/

mkdir -p ${DESTINATION}
systemctl stop $SERVICE 
tar cvzf "examibur-dev-${DATE}.tar.gz" "$VOLUMES_DIR"
systemctl start $SERVICE
