# Install Apache
package 'apache2'

# Set up the home page
template "/var/www/html/index.html" do
  source "index.html.erb"
  variables( :name => 'STPCon Fall 2017' )
end
