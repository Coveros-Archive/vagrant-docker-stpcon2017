# Install the MySQL server
mysql_service 'default' do
  port '3306'
  version '5.5'
  initial_root_password 'MyHovercraftIsFullOfEels'
  action [:create, :start]
end

# Install the mysql2 Ruby gem for the database resources
mysql2_chef_gem 'default' do
  action :install
end

# Collect connection info for reuse
mysql_connection_info = {
	:host => node['statedb']['database']['host'],
	:username => node['statedb']['database']['root_username'],
	:password => node['statedb']['database']['root_password']
}

# Create database
mysql_database node['statedb']['database']['dbname'] do
  connection mysql_connection_info
  action [:drop, :create]
end

# Make a database user
mysql_database_user node['statedb']['database']['statedb_username'] do
  connection mysql_connection_info
  password node['statedb']['database']['statedb_password']
  database_name node['statedb']['database']['dbname']
  host node['statedb']['database']['host']
  action [:create, :grant]
end

# Populate database
create_tables_script_path = File.join(Chef::Config[:file_cache_path], 'statedb.sql')
cookbook_file create_tables_script_path do
  source 'statedb.sql'
  owner 'root'
  group 'root'
  mode '0600'
end
mysql_database node['statedb']['database']['dbname'] do
  connection mysql_connection_info
  sql { ::File.open(create_tables_script_path).read }
  action :query
end
