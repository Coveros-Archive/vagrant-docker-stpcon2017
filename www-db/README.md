# Vagrant Database and Web Server

Uses Chef to stand up a MySQL database server running on one system and 
an Apache web server with a single PHP page that pulls the data from
the database. The web site will be available on http://192.168.33.11/
from the local system.

The SQL file that populates the MySQL table is `cookbooks/statedb/files/default/states-data.sql`.
Each SQL file can only contain one SQL statement that is executed due to the way it is 
being sent to the MySQL client. Change the commented line in that file to switch between 
US states and Canadian provinces.
 
The only custom Chef cookbook is under `cookbooks/statedb`. 

All the other Chef cookbooks are downloaded from https://supermarket.chef.io/cookbooks/.
This isn't a good solution. It is better to use Berksfile to manage the 
cookbooks and their dependencies automatically. But that would require Chef software to be 
installed on the host system.

## To stand up both systems:

    vagrant up
		
