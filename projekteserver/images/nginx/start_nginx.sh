#!/bin/bash
set -e
echo "HERE"
ls -A /etc/dehydrated/
echo "x$(ls -A /etc/dehydrated/)x"
if [ "$(ls -A /etc/dehydrated/)" == "domains.txt" ]; then
    echo "there"
    # Delete specific config - to just setup let's encrypt!
    rm -R /etc/nginx/conf.d/
    nginx # Start nginx in background
    sleep 5 # Wait until its up
    dehydrated --register --accept-terms
    dehydrated --cron   # register certificates
else
    echo "FUCK YOU!"
    #    nginx -g "daemon off;"
fi

