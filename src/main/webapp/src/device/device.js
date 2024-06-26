'use strict';

angular.module('homeon').controller('deviceCtrl',
	function ($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope, $location, $mdMedia) {
		$rootScope.statusMenu = true;
		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';
		$scope.devices = [];

		var id = $rootScope.authDetails.id;
		var deviceFindId = deviceUrl + '/findByClientId/' + id;

		$scope.card = { name: "Luz", description: "Luz do quarto", active: false, iconPath: "../../assets/ico/lamp.ico" };
		$scope.divEmptyList = true;

		RestSrv.find(deviceFindId, function (status, data) {
			if (data != null && data.length > 0) {
				$scope.devices = data;
				$scope.divEmptyList = false;
			} else {
				$scope.devices.push($scope.card);
			}
		});

		$scope.deviceDetails = function (id) {
			$location.path('/device/' + id);
		}

		$scope.removeItem = function (index) {
			$scope.arrayOfCards.splice(index, 1);
		}

		$scope.save = function (device) {
			device.client = $rootScope.authDetails;
			RestSrv.add(deviceUrl, device, function (status, data) {
				if (status === 'ok') {
					data.client = null;
					$scope.devices = [];
					$scope.devices.push(data);
					$scope.divEmptyList = false;
					ngNotify.set('Dispositivo Cadastrado com Sucesso!.', { type: 'success' });
					return;
				} else {
					ngNotify.set('Dispositivo não cadastrado!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};

		$scope.showDialogNewDevice = function ($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/device/dialog/newDeviceDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newDeviceDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			}).then(function (data) {

				if ($scope.devices[0].id === undefined) {					
					$scope.devices = [];
				}
				$scope.devices.push(data);
			}, function () {
			});
		};

		$scope.updateStatus  = function(device) {
			var mqtt = {};
			mqtt.user = $rootScope.authDetails.id;
			mqtt.device = device.id;
			mqtt.action = (device.active === true)? 1 : 0;
			mqtt.pin = device.port.id;

			if (mqtt.pin != undefined) {
				if ( $rootScope.authDetails.connectWebSocket) {
				 
					WebSocketService.send(device); 
				} else {
					RestSrv.edit(deviceUrl+"/updateStatus/", mqtt, function(status, data) {
						if (status === 'ok') {
							ngNotify.set('Status Atualizado com Sucesso!.', { type: 'success' });
							return;
						} else {
							ngNotify.set('Status não Atualizado!.', { type: 'error', duration: 5000 });
							return;
						}
					});		
				}				
			}
		};			
	});
