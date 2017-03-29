FROM nginx:1.11-alpine

MAINTAINER Raphael Zimmermann <raphael.zimmermann@hsr.ch>

ARG DEHYDRATED_VERSION=v0.4.0

RUN apk add --no-cache curl bash openssl
ADD https://raw.githubusercontent.com/lukas2511/dehydrated/${DEHYDRATED_VERSION}/dehydrated /usr/local/bin/
RUN chmod a+x /usr/local/bin/dehydrated && mkdir -p /var/www/dehydrated \
    && mkdir -p /usr/local/etc/dehydrated/
ADD config /usr/local/etc/dehydrated/config

COPY nginx.conf /etc/nginx/nginx.conf
