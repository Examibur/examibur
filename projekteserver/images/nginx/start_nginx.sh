#!/bin/bash
set -e
ls -A /etc/dehydrated/
if [ "$(ls -A /etc/dehydrated/)" == "domains.txt" ]; then
    echo "fetching Let's encrypt certificates the first time..."
    # Delete specific config - to just setup let's encrypt!
    rm -R /etc/nginx/conf.d/
    nginx # Start nginx in background
    sleep 5 # Wait until its up
    dehydrated --register --accept-terms
    dehydrated --cron   # register certificates
    echo "OK - please restart this container!"
else
    nginx -g "daemon off;"
fi

