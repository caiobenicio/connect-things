'use strict'

angular.module('homeon')
	.controller('homeCtrl', function ($scope, $rootScope, $location, RestSrv, SERVICE_PATH) {

		var homeUrl = SERVICE_PATH.PRIVATE_PATH + '/home';
		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		var deviceUrl = SERVICE_PATH.PRIVATE_PATH + '/device';

		$rootScope.statusMenu = true;
		var id = $rootScope.authDetails.id;
		$scope.homes = [];
		$scope.home = {};
		$scope.client = {};
		$scope.boardOnline = 0;
		$scope.deviceActive = 0;

		var boardFindId = boardUrl + '/findByClientId/' + id;
		RestSrv.find(boardFindId, function(status, data) {
			if (data != null) {
				$scope.client.boards = data;

				for (let index = 0; index < $scope.client.boards.length; index++) {
					const placa = $scope.client.boards[index];
					if (placa.status) {
						$scope.boardOnline++
					}			
				}					
			}
		});

		var deviceFindId = deviceUrl + '/findByClientId/' + id;
		RestSrv.find(deviceFindId, function (status, data) {
			if (data != null) {
				$scope.client.devices = data;

				for (let index = 0; index < $scope.client.devices.length; index++) {
					const placa = $scope.client.devices[index];
					if (placa.active) {
						$scope.deviceActive++
					}			
				}				
			}
		});		

		$scope.saveHome = function () {
			$scope.client.id = id;
			$scope.home.client = $scope.client;
			RestSrv.add(homeUrl, $scope.home, function (status, data) {
				if (data != null) {
					$scope.homes.push(data);
				}
			});
		}

		$scope.editHome = function (home) {
			if (home.id) {
				RestSrv.edit(homeUrl, home, function () {
				});
			}
		};

		$scope.deleteHome = function (home, index) {
			RestSrv.delete(homeUrl, home, function () {
				$scope.homes.splice(index);
			});
		};

		$scope.board = function() {
			$location.path('/board');
		};

		$scope.device = function() {
			$location.path('/device');
		};
	});
