# Our First Dockerfile

Creates an Ubuntu-based Docker image the has the `fortune` game installed
and runs it anytime the container is run.
 
## To create:

    docker build -t coveros/fortune2 .
		
## To run:

    docker run coveros/fortune2
