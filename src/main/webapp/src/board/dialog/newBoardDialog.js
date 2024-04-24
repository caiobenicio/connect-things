'use strict';

angular.module('homeon').controller('newBoardDialogCtrl',
	function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope) {
		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		$scope.board = {};
		$scope.board.type = 'ESP8266';
		$scope.board.topicPublish = 'clientweb/outTopic';
		$scope.board.topicSubscribe = 'clientweb/inTopic';

		$scope.saveBoard = function(board) {
			$scope.board.client = $rootScope.authDetails;
			RestSrv.add(boardUrl, board, function(status, data) {
				if (status === 'ok') {
					//userService.addProduct(data);
					ngNotify.set('Placa Cadastrada com Sucesso!.', { type: 'success' });
					$mdDialog.hide(data);
					//$mdDialog.cancel();
					return;
				} else {
					ngNotify.set('Placa não cadastrada!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.typesESP8266 = ('Arduino  WeMos D1 R1').split('  ').map(function(typeESP8266) {
			return { abbrev: typeESP8266 };
		});

		$scope.typesESP32 = ('NodeMCU-32S  Node32s  Nano32').split('  ').map(function(typeESP32) {
			return { abbrev: typeESP32 };
		});

	});
