'use strict';

angular.module('homeon').controller('newDeviceDialogCtrl',
	function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope) {
		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';

		$scope.saveDevice = function(device) {
			device.active = false;
			$scope.device.client = $rootScope.authDetails;
			RestSrv.add(deviceUrl, device, function(status, data) {
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

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

	});
