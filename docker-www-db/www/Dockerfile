# Builds a php image for the statedb web site

FROM php:7.0-apache
RUN docker-php-ext-install mysqli
COPY ./src/ /var/www/html
