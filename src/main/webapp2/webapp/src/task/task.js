'use strict';

angular.module('homeon').controller(
	'taskCtrl',
	function($scope, RestSrv, SERVICE_PATH, $location, ngNotify, $http, $rootScope, WebSocketService) {
		var taskUrl = SERVICE_PATH.PRIVATE_PATH + '/task';
		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		$scope.boards = $rootScope.authDetails.boards;


		if ($scope.boards == []) {
			var id = $rootScope.authDetails.id;
			var boardFindId = boardUrl + '/findByClientId/' + id;
			RestSrv.find(boardFindId, function(status, data) {
				if (data != null) {
					$scope.boards = data;
					$rootScope.authDetails.boards = $scope.boards;
				}
			});
		}

		$scope.sendCommand = function(task) {
			
			WebSocketService.send($scope.message);
//			$http.post(taskUrl + "?boardId=" + task.board + "&pin=" + task.pin + "&command=" + task.command, null).then(function(response) {
//
//				if (response.data)
//					ngNotify.set('Comando Enviado com Sucesso!.', { type: 'success' });
//
//			}, function(response) {
//
//
//			});
		};
		
		$scope.sendMessage = function() {
			WebSocketService.send($scope.message);
		};		
	});
