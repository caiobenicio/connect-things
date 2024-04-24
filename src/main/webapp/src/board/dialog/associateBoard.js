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
			var port = { id: pin.port.id, name: pin.port.name, type: pin.port.type }

			device.client = $rootScope.authDetails;
			device.client.boards = null;
			device.port = port;
			//device.port.device.client = null;
			//device.port.device.port = null;

			RestSrv.edit(deviceUrl+"/updatePort/"+device.port.id+"/"+device.id, null, function (status, data) {
				if (status === 'ok') {
					ngNotify.set('Porta Associada com Sucesso!.', { type: 'success' });
					$mdDialog.hide(data);
					return;
				} else {
					ngNotify.set('Porta não Associada!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};		
	});
