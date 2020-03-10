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
    
    var initialize = function() {
      socket = new SockJS('/ws');
      stompClient = Stomp.over(socket);
      
      stompClient.connect({}, function(frame){
      	stompClient.subscribe('/channel/casa-caio', onMessageReceived);
      })
    };
   
      service.send = function(message) {
        var id = Math.floor(Math.random() * 1000000);
        
        stompClient.send('/app/chat/casa-caio/addUser',
        	    {},
        	    JSON.stringify({sender: 'clientWeb', type: 'JOIN'})
         );
      };
      
      var reconnect = function() {
        $timeout(function() {
          initialize();
        }, pathSocket.RECONNECT_TIMEOUT);
      };
      
      var onMessageReceived = function(payload) {
    	  var message = JSON.parse(payload.body);
    	  console.log(message);
    	  
    	    var chatMessage = {
    	    	      sender: 'clientWeb',
    	    	      content: '/join casa-caio',
    	    	      type: 'CHAT'
    	    	    };
    	    	    stompClient.send('/app/chat/casa-caio/sendMessage', {}, JSON.stringify(chatMessage));
      }
      
    initialize();
    
   // service.send(msg);
    
    return service;
  });
