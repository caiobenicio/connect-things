'use strict';

angular.module('homeon').controller('boardDetailsCtrl',
	function($scope, RestSrv, SERVICE_PATH, $routeParams, $rootScope, ngNotify) {
		$rootScope.statusMenu = true;
		$scope.params = $routeParams;

		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		$scope.board = {};
		$scope.todos = [{port:'P12', type:'I'}, {port:'P11', type:'O'}];

		var portUrl = SERVICE_PATH.PRIVATE_PATH + '/port';
		$scope.ports = [];

		var boardFindId = boardUrl + '/findById/' + $scope.params.id;
		RestSrv.find(boardFindId, function(status, data) {
			if (data != null) {
				$scope.board = data;
			}
		});

		RestSrv.find(portUrl + "/findByBoardId/" + $scope.params.id, function(status, data) {
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

		$scope.savePorts = function(ports) {

			angular.forEach(ports, function(value, key) {
				var port = {
					port: value.port,
					type: value.type,
					board: $scope.board
				};

				RestSrv.add(portUrl, port, function(status, data) {
					if (status === 'ok') {
						ngNotify.set('Porta(s) Cadastradas com Sucesso!.', { type: 'success' });
						return;
					} else {
						ngNotify.set('Porta(s) não cadastrada!.', { type: 'error', duration: 5000 });
						return;
					}
				});

			});

		};

		$scope.getPort = function(id) {
			RestSrv.find(portUrl + "/findByPorts/" + id, function(status, data) {
				if (data != null && data != []) {
					$scope.ports = data;
				}
			});
		};
	});
