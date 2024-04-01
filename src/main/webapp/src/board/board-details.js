'use strict';

angular.module('homeon').controller('boardDetailsCtrl',
	function($scope, RestSrv, SERVICE_PATH, $routeParams, $rootScope, ngNotify) {
		$scope.params = $routeParams;

		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		$scope.board = {};

		var portUrl = SERVICE_PATH.PRIVATE_PATH + '/port';
		$scope.ports = [];

		var boardFindId = boardUrl + '/findById/' + $scope.params.id;
		RestSrv.find(boardFindId, function(status, data) {
			if (data != null) {
				$scope.board = data;
			}
		});
		
		RestSrv.find(portUrl+"/findByBoardId/"+$scope.params.id, function(status, data) {
			if (data != null && data != []) {
				$scope.ports = data;
			}
		});
		
		$scope.editBoard = function(board) {
			$scope.board.client = $rootScope.authDetails;
			RestSrv.edit(boardUrl, board, function(status, data) {
				if (status === 'ok') {
					$scope.sendWebSocket();
					ngNotify.set('Placa Atualizada com Sucesso!.', { type: 'success' });
					return;
				} else if (status == 204) {
					ngNotify.set('Placa Atualizada com Sucesso!.', { type: 'success' });
					return;
				} else {
					ngNotify.set('Placa não Atualizada!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};

		$scope.sendWebSocket = function() {
			$scope.command = {
				user: $rootScope.authDetails.id,
				topic: $scope.board.topicPublish,
				msgType: "P"
			};

			WebSocketService.send($scope.command);
		}

		WebSocketService.onMessage(function(message) {
			var received = angular.fromJson(message);
			angular.forEach(received.pinsIn, function(value, key) {
				var port = {
					port: value,
					type: "I"
				};

				$scope.ports.push(port);
			});

			angular.forEach(received.pinsOut, function(value, key) {
				var port = {
					port: value,
					type: "O"
				};

				$scope.ports.push(port);
			});
		});

		$scope.savePorts = function(ports) {

			$scope.portList = [];

			angular.forEach(ports, function(value, key) {
				var port = {
					port: value.port,
					type: value.type,
					board: $scope.board
				};

				$scope.portList.push(port);
			});

			RestSrv.add(portUrl, $scope.portList, function(status, data) {
				if (status === 'ok') {
					ngNotify.set('Porta(s) Cadastradas com Sucesso!.', { type: 'success' });
					return;
				} else {
					ngNotify.set('Porta(s) não cadastrada!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};

		$scope.getPort = function(id) {
			RestSrv.find(portUrl+"/findByPorts/"+id, function(status, data) {
				if (data != null && data != []) {
					$scope.ports = data;
				}
			});	
		};		
	});
