'use strict';

angular.module('homeon')

	.controller('SocketCtrl', function($mdDialog, $scope, $mdMedia, $mdToast, RestSrv, SERVICE_PATH, $rootScope) {
		$rootScope.statusMenu = true;
		$scope.message;
		var socket;

		$scope.connect = function() {
			socket = new WebSocket("ws://localhost:8081/user");
		};

		$scope.send = function() {
			var data = JSON.stringify({
				'user': $scope.message
			})
			socket.send(data);
		};

		$scope.onMessage = function(callback) {
			socket.onmessage = callback;
		};

		$scope.onClose = function(callback) {
			socket.onclose = callback;
		};

	});