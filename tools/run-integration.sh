#!/bin/bash
set -e
# Build the latest integration image...
docker build -t examibur/ui_tests -f ../docker/Dockerfile.integration ../docker/

# Create named volumes (for performance)
docker volume create --name examibur_int_userhome_gradle > /dev/null

# Run the image
REPO_BASE=$( cd "$( dirname "$0" )/.." && pwd )
docker run --rm -i \
    -v "${REPO_BASE}/examibur":/src/:z \
    -v examibur_int_userhome_gradle:/home/examibur/.gradle/ \
    --user examibur \
    -e UI_TEST_URL=http://tomcat:8080/ \
    -e DB_HOST=postgres \
    -e DB_USER=examibur \
    -e DB_PASSWORD=m4U6ctWpEZE801T \
    -e LOG_LEVEL=debug \
    -e LOG_FILE=examibur.log \
    --network="examibur_examibur" \
    examibur/ui_tests
