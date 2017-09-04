# Install Apache and start the service.
httpd_service 'state' do
  mpm 'prefork'
  action [:create, :start]
end

# Add the site configuration.
httpd_config 'state' do
  instance 'state'
  source 'state.conf.erb'
  notifies :restart, 'httpd_service[state]'
end

# Create the document root directory.
directory node['statedb']['document_root'] do
  recursive true
end

# Set up the home page
template "#{node['statedb']['document_root']}/index.php" do
  source "index.php.erb"
  variables( 
		:name => 'STPCon Fall 2017',
		:db_host => "192.168.33.10",
		:db_name => node['statedb']['database']['dbname'],
		:db_user => node['statedb']['database']['statedb_username'],
		:db_pass => node['statedb']['database']['statedb_password']
	)
end

# Install the mod_php5 Apache module.
httpd_module 'php5' do
  instance 'state'
end

# Install php5-mysql.
package 'php5-mysql' do
  action :install
  notifies :restart, 'httpd_service[state]'
end
