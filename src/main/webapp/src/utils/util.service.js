'use strict';

angular.module('homeon')
  .service('HttpRequestSrv', function($q, $timeout, $http, ngNotify){
    return function(url, method, data, callback){
      var requestParams = {
        method: method,
        url: url,
        headers: {'Content-type': 'application/json'},
        data: data
      };
      $http(requestParams).then(
        function successCallback(response){
          callback && callback(response.data);
        });
    };
  })
  .service('RestSrv', function(HttpRequestSrv){
    var restFactory = {};
    
    // Find all data.
    restFactory.find = function(url, callback){
      HttpRequestSrv(url, 'GET', {}, callback);
    };
    
    // Aadd a new data.
    restFactory.add = function(url, data, callback){
      HttpRequestSrv(url, 'POST', data, callback);
    };
    
    // Edit a data.
    restFactory.edit = function(url, data, callback){
      HttpRequestSrv(url, 'PUT', data, callback);
    };
    
    // Delete a data.
    restFactory.delete = function(url, data, callback){
      HttpRequestSrv(url, 'DELETE', data, callback);
    };
    return restFactory;
  })
  .service("WebSocketService", function($q, $timeout) {
    
	var service = {} ;
    var pathSocket = {};
    var socket = null;
    var stompClient = null;
    
    var msg = "caio";
    
    pathSocket.RECONNECT_TIMEOUT = 30000;
    pathSocket.BASE_URL = "/stomp";
    pathSocket.CHAT_TOPIC = "/clientWeb/sensor";
    pathSocket.CHAT_BROKER = "/app/sensor";
    
    var initialize = function() {
      socket = new SockJS(pathSocket.BASE_URL);
      stompClient = Stomp.over(socket);
      
      stompClient.connect({}, function(frame){
      	stompClient.subscribe(pathSocket.CHAT_TOPIC, function(data) {
    		console.log(data);
    	});
      })
    };
   
      service.send = function(message) {
        var id = Math.floor(Math.random() * 1000000);
        
        stompClient.send(pathSocket.CHAT_BROKER, {
          priority: 9
        }, JSON.stringify({
          message: message,
          id: id
        }));
      };
      
      var reconnect = function() {
        $timeout(function() {
          initialize();
        }, pathSocket.RECONNECT_TIMEOUT);
      };
      
    initialize();
    
   // service.send(msg);
    
    return service;
  });
