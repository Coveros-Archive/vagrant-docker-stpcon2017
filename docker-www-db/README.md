# Docker Database and Web Server

Uses Docker Compose to stand up a MySQL database server running in one container and 
a PHP-enabled Apache web server on another with a single PHP page that pulls the data from
the database. 

The MySQL database is a stock mysql Docker image from https://hub.docker.com/.
The SQL file that populates the MySQL table is `sql/02-states-data.sql`.
The SQL data is only loaded when the container is created, and the container
will remain cached when stopping and starting it.

The PHP-enabled Apache web server is a based on the php Docker image from https://hub.docker.com/.
It automatically builds using the Dockerfile in `www/Dockerfile` and adds the `mysqli` 
library so it can access the database. 

The PHP file stays resident on the host system in `www/src/index.php`. Any changes made locally 
can be instantly reloaded in the browser.

## To stand up both containers:

    docker-compose up -d
		
## Visit the site:

    http://ip/

## To find the IP address the web server is listening on:

### Running Docker Toolkit or Docker for Linux: 
	docker-machine ip
### Running Docker for Windows:
The IP address is localhost or 127.0.0.1.

## To reload the data into the table (by recreating the containers):

    docker-compose stop
    docker-compose rm -v
    docker-compose up -d

## Troubleshooting:

If you get 

    Warning: mysqli::__construct(): (HY000/2002): Connection refused in /var/www/html/index.php on line 31
    Connection failed: Connection refused

it is likely because the database hasn't started up yet. Wait a few seconds and try again.
