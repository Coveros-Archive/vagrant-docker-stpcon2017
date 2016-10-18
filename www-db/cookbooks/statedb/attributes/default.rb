def random_password
  require 'securerandom'
  SecureRandom.base64
end

normal_unless['statedb']['database']['root_password'] = random_password
default['statedb']['database']['dbname'] = 'statedb'
default['statedb']['database']['host'] = '127.0.0.1'
default['statedb']['database']['root_username'] = 'root'

normal_unless['statedb']['database']['statedb_password'] = random_password
default['statedb']['database']['statedb_username'] = 'state_user'
