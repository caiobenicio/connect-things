'use strict';

angular.module('homeon')
	.factory('WebSocketService', function($websocket, SERVICE_PATH) {
		// Open a WebSocket connection
		var url = SERVICE_PATH.WS_PUBLIC_PATH;
		var dataStream = $websocket(url);

		var methods = {
			onMessage: function(callback) {
				dataStream.onMessage(function(event) {
					callback(event.data);
				});
			},		
			send: function(message) {
				dataStream.send(JSON.stringify(message));
			}
		};

		return methods;
	});


	//	var ws = $websocket(url);
	//	var service = {
	//		send: function(message) {
	//			ws.send(message);
	//		},
	//		onMessage: function(callback) {
	//			ws.onMessage(function(event) {
	//				callback(event.data);
	//			});
	//		},
	//		onClose: function() {
	//			ws.onClose(function() {
	//				console.log('WebSocket connection closed');
	//			});
	//		},			
	//	};
	//	return service;
	//})
	
