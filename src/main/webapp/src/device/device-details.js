'use strict';

angular.module('homeon').controller('deviceDetailsCtrl',
	function($scope, RestSrv, SERVICE_PATH, $routeParams, $rootScope, ngNotify, $mdDialog, $mdMedia, WebSocketService) {
		$rootScope.statusMenu = true;
		$scope.params = $routeParams;

		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';
		$scope.device = {};
		$scope.associatePortBoard = [];
		
		RestSrv.find(deviceUrl + '/' + $scope.params.id, function(status, data) {
			if (data != null) {
				$scope.device = data;
			}
		});

		$scope.editDevice = function(device) {
			$scope.device.client = $rootScope.authDetails;
			RestSrv.edit(deviceUrl, device, function(status, data) {
				if (status === 'ok') {
					ngNotify.set('Dispositivo Atualizado com Sucesso!.', { type: 'success' });
					return;
				} else {
					ngNotify.set('Dispositivo não Atualizado!.', { type: 'error', duration: 5000 });
					return;
				}
			});
		};

		$scope.showDialogAssociateBoard = function($event, deviceName) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/device/dialog/newAssociateBoardDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newAssociateBoardDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
				locals:{dataToPass: deviceName}, 
			}).then(function(data) {
					$scope.associatePortBoard.push(data);
				}, function() {	}
			);
		};

		$scope.updateStatus  = function(device) {
			device.client = $rootScope.authDetails;
            if ( $rootScope.connectWebSocket ) {
				 
				WebSocketService.send(device); 
            } else {
				RestSrv.edit(deviceUrl+"/updateStatus/"+device.port.id+"/"+device.id, null, function(status, data) {
					if (status === 'ok') {
						ngNotify.set('Status Atualizado com Sucesso!.', { type: 'success' });
						return;
					} else {
						ngNotify.set('Status não Atualizado!.', { type: 'error', duration: 5000 });
						return;
					}
				});		
			}
		};		

	});
