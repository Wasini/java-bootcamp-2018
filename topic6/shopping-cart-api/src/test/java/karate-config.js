function() {
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  var port = 8080;
  var protocol = 'http';
  var config = { BaseUrl: protocol + '://127.0.0.1:' + port };
  return config;
}