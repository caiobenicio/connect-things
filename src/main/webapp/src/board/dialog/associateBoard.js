'use strict';

angular.module('homeon').controller('associateBoardCtrl',
	function ($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope, $location, dataToPass) {
		$scope.emptyDevices = true;
		$scope.devices = [];
		var id = $rootScope.authDetails.id;

		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';
		var deviceFindId = deviceUrl + '/findByClientId/' + id;

		var portUrl = SERVICE_PATH.PRIVATE_PATH + '/port';

		$scope.cancel = function () {
			$mdDialog.cancel();
		};

		RestSrv.find(deviceFindId, function (status, data) {
			if (data != null && data != "") {
				$scope.devices = data;
				$scope.emptyDevices = false;
			}
		});

		$scope.savePortDevice = function (device) {
			var pin = dataToPass
			var port = { port: pin.name, type: pin.type, board: dataToPass.board, device: device }

			RestSrv.add(portUrl, port, function (status, data) {
				if (status === 'ok') {
					ngNotify.set('Dispositivo Cadastrado com Sucesso!.', { type: 'success' });
					$mdDialog.hide(data);
					return;
				} else {
					ngNotify.set('Dispositivo não cadastrado!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};
	});
