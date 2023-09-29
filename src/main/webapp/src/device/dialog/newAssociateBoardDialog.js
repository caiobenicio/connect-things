'use strict';

angular.module('homeon').controller('newAssociateBoardDialogCtrl',
	function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope, dataToPass, $mdMedia, $location) {
		$scope.dataLoaded = true;
		$scope.dataEmpty = false;
		$scope.name = dataToPass;
		var id = $rootScope.authDetails.id;
		$scope.boards = [];
		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board/findByClientId/' + id;

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		RestSrv.find(boardUrl, function(status, data) {
			if (data != null) {
				$scope.boards = data;
				$scope.dataLoaded = false;
				$scope.dataEmpty = true;
			}
		});

		$scope.showDialogNewBoardAssociate = function($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/board/dialog/newBoardDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newBoardDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			})
				.then(function(data) {
					$scope.boards.push(data);
				}, function() {

				});
		};

		$scope.savePortDevice = function(boardId) {

			var queryParams = $location.search();

			var deviceId = queryParams.param1;

			$scope.boardDevice = {deviceId: deviceId, boardId:  boardId};
			RestSrv.add(deviceUrl, boardDevice, function(status, data) {
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
