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
		
## Troubleshooting:

If you get ``Error executing action `install` on resource 'apt_package[mysql-server-5.5]'``
then it is because the download package for mysql-server-5.5 on Ubuntu Trusty was updated. 
This is what happened during the demo.

Just edit `cookbooks/mysql/libraries/helpers.rb` and change the `'5.5' && trusty?` line (line number 187)
to match the new file name. For example, I changed:

    return '5.5.52-0ubuntu0.14.04.1' if major_version == '5.5' && trusty?

to be

    return '5.5.53-0ubuntu0.14.04.1' if major_version == '5.5' && trusty?
