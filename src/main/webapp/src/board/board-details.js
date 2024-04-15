'use strict';

angular.module('homeon').controller('boardDetailsCtrl',
	function($scope, RestSrv, SERVICE_PATH, $routeParams, $rootScope, ngNotify, $mdMedia, $mdDialog, WebSocketService) {
		$rootScope.statusMenu = true;
		$scope.params = $routeParams;
		var id = $rootScope.authDetails.id;

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

		RestSrv.find(portUrl + "/findByBoardId/" + $scope.params.id, function(status, data) {
			if (data != null && data != []) {
				$scope.ports = data;
			}
		});

		$scope.editBoard = function(board) {
			$scope.board.client = $rootScope.authDetails;
			$scope.board.ports = $scope.ports;
			RestSrv.edit(boardUrl, board, function(status, data) {
				if (status === 'ok') {
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

		$scope.getPort = function(id, topicPublish, boardId) {
			RestSrv.find(portUrl + "/findByPorts?id=" + id + "&topic=" + topicPublish
				+ "&boardId="+boardId, function(status, data) {
				if (data != null && data != []) {
					$scope.ports = data;
				}
			});
		};

		$scope.showDialogassociateBoard = function(board, port, $event) {
			var objBoard = { board: board, port: port };
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/board/dialog/associateBoard.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'associateBoardCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
				locals:{dataToPass: objBoard},
			})
			.then(function(data) {
				//$scope.boards.push(data);
			}, function() {
			});
		};

		$scope.delete = function(board) {
			RestSrv.delete(boardUrl, board, function() {});	
		};	
		
		WebSocketService.onMessage(function(event) {
			var data = JSON.parse(event);
			if (data.msgType === "P") {
				angular.forEach(data.pinsIn, function(value, key) {
					$scope.ports.push({name: value, type:'I'});
				});

				angular.forEach(data.pinsOut, function(value, key) {
					$scope.ports.push({name: value, type:'O'});
				});
			}
		});			
	});
