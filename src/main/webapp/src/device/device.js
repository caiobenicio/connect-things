'use strict';

angular.module('homeon').controller('deviceCtrl',
	function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope, $location, $mdMedia) {
		$rootScope.statusMenu = true;
		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';
		$scope.devices = [];
		
		var id = $rootScope.authDetails.id;
		var deviceFindId = deviceUrl + '/findByClientId/' + id;

		$scope.card = { name: "Luz", description: "Luz do quarto", active: false, iconPath: "../../assets/svg/lamp.svg"};
		$scope.divEmptyList = true;
		
		RestSrv.find(deviceFindId, function(status, data) {
			if (data != null && data.length >0) {
				$scope.devices = data;
				$scope.divEmptyList = false;
				//$rootScope.authDetails.devices = $scope.devices;
			} else {
				$scope.devices.push($scope.card);
			}
		});

		$scope.deviceDetails = function(id) {
			$location.path('/device/' + id);
		}
		
		$scope.removeItem = function(index) {
			$scope.arrayOfCards.splice(index, 1);
		}
		
		$scope.save = function(device) {
			device.client = $rootScope.authDetails;
			RestSrv.add(deviceUrl, device, function(status, data) {
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

		$scope.showDialogNewDevice = function($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/device/dialog/newDeviceDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newDeviceDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			}).then(function(data) {
					$scope.devices.push(data);
				}, function() {

			});
		};
	});
