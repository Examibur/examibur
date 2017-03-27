FROM nginx:1.11-alpine

MAINTAINER Raphael Zimmermann <raphael.zimmermann@hsr.ch>

RUN apk --update add openssl # Keep openssl up-to-date
COPY nginx.conf /etc/nginx/nginx.conf
