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
  });

angular.module("homeon").service("WebSocketService", function($q, $timeout) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/chat";
    service.CHAT_TOPIC = "/topic/message";
    service.CHAT_BROKER = "/app/portfolio";
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message) {
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        message: message,
        id: id
      }));
      messageIds.push(id);
    };
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.message;
      out.time = new Date(message.time);
      if (_.contains(messageIds, message.id)) {
        out.self = true;
        messageIds = _.remove(messageIds, message.id);
      }
      return out;
    };
    
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
        listener.notify(getMessage(data.body));
      });
    };
    
    var initialize = function() {
      socket.client = new SockJS('/chat');
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
  });
