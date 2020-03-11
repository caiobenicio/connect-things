'use strict';

angular.module('homeon')
  .service("WebSocketService", function($q, $timeout, $rootScope) {
    
	  var stompClient = null;
	  var currentSubscription;
	  var username = null;
	  var roomId = null;
	  var topic = null;
	  var service = {};
    
   var RECONNECT_TIMEOUT = 30000;
    
   service.connect = function() {
      var socket = new SockJS('/ws');
      stompClient = Stomp.over(socket);
      
      stompClient.connect({}, onConnected, onError)
    };
   
    
    function onConnected() {
    	  enterRoom($rootScope.authDetails.name);
    	}
    
 // Leave the current room and enter a new one.
    function enterRoom(newRoomId) {

     var roomId = newRoomId;
     var topic = '/app/chat/'+newRoomId;

      if (currentSubscription) {
        currentSubscription.unsubscribe();
      }
      currentSubscription = stompClient.subscribe('/channel/'+roomId, onMessageReceived);

      stompClient.send(topic+'/addUser',
        {},
        JSON.stringify({sender: roomId, type: 'JOIN'})
      );
    }

    	function onError(error) {
    		console.log(error);
    	}
   
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
        }, RECONNECT_TIMEOUT);
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
      
    
   // service.send(msg);
    
    return service;
  });