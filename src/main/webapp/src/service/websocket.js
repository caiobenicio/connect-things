'use strict';

angular.module('homeon')
	.factory('WebSocketService', function ($rootScope, $websocket, SERVICE_PATH) {
		// Open a WebSocket connection
		var id = $rootScope.authDetails.id;
		var url = SERVICE_PATH.WS_PATH;
		var dataStream = $websocket(url);

		var methods = {
			onMessage: function (callback) {
				dataStream.onMessage(function (event) {
					callback(event.data);
				});
			},
			send: function (message) {
				console.log('WebSocket send=' + message);
				dataStream.send(JSON.stringify(message));
			},
			onOpen: function () {
				dataStream.send('{"user":' + id + '}',);
			},
			onClose: function () {
				dataStream.onClose(function () {
					console.log('WebSocket connection closed');
				});
			},
		};

		return methods;
	});

