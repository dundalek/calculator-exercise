require 'sinatra'
load 'calculator.rb'

get '/differentiate/*' do
  begin
    Calculator.new(params['splat'].first).differentiate.to_s
  rescue Exception => e
    [400, 'Invalid input: ' + e.to_s]
  end
end
