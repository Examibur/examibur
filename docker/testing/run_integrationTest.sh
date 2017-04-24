#!/bin/bash
XVFB_WHD=${XVFB_WHD:-5120x2880x16}

# Start Xvfb
Xvfb :99 -ac -screen 0 $XVFB_WHD -nolisten tcp & xvfb=$!

export DISPLAY=:99

chown examibur:examibur /src/screenshots
runuser -l examibur -c "export UI_TEST_URL=$UI_TEST_URL && \
                           export DB_HOST=$DB_HOST && \
                           export DB_USER=$DB_USER && \
                           export DB_PASSWORD=$DB_PASSWORD && \
                           export LOG_LEVEL=$LOG_LEVEL && 
                           export LOG_FILE=$LOG_FILE && \
                           export DISPLAY=:99 && cd /src/ && \
                           ./gradlew clean integrationTest"

