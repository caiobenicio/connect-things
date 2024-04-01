'use strict'

angular.module('homeon')
	.controller('homeCtrl', function($scope, $rootScope, $mdDialog, $mdToast, RestSrv, SERVICE_PATH) {
		$('[data-toggle="tooltip"]').tooltip();
		$rootScope.statusMenu = true;
		$scope.mostrarElemento = true;
		$scope.showAddHome = false;
		$scope.homeName = "";
		var id = $rootScope.authDetails.id;
		var homeUrl = SERVICE_PATH.PRIVATE_PATH + '/home';
		$scope.homes = [];
		$scope.home = {};
		$scope.home = { name: "" };
		$scope.client = {};

		var homeFindId = homeUrl + '/findByClientId/' + id;
		RestSrv.find(homeFindId, function(status, data) {
			if (data != null) {
				$scope.homes = data;
				$scope.mostrarElemento = false;
			}
		});

		$scope.showFormHome = function() {
			$scope.showAddHome = !$scope.showAddHome;
		}

		$scope.saveHome = function() {
			$scope.client.id = id;
			$scope.home.name = $scope.homeName;
			$scope.home.client = $scope.client;
			RestSrv.add(homeUrl, $scope.home, function(status, data) {
				if (data != null) {
					$scope.homes.push(data);
				}
			});

			$scope.homeName = "";
			$scope.mostrarElemento = false;
		}

		$scope.closeAddHome = function() {
			$scope.showAddHome = false;
		}

		$scope.editHome = function(home) {
			if (home.id) {
				RestSrv.edit(homeUrl, home, function() {
				});
			}
		};

		$scope.deleteHome = function(home, index) {
			RestSrv.delete(homeUrl, home, function() {
				$scope.homes.splice(index);
				if ($scope.homes.length === 0) {
					$scope.mostrarElemento = true;
				}
			});
		};

		$scope.addHome = function() {
			var actions = $("table td:last-child").html();
			var index = $("table tbody tr:last-child").index();
			var actions = $("table td:last-child").html();
			var row = '<tr>' +
				'<td class="text-center"></td>' +
				'<td><input type="text" class="form-control" ng-model="message" required></td>' +
				'<td>' + actions + '</td>' +
				'</tr>';

			$("table").append(row);

			$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
			$('[data-toggle="tooltip"]').tooltip();
		};

		//	  var isGateway = $rootScope.authDetails.isGateway;
		//	  if(isGateway) {
		//		  RestSrv.findByEmail('/api/private/user', $rootScope.authDetails.email, function(response) {
		//		        console.log(response);
		//	      });
		//	  }
		//      
		//  WebSocketService.connect();


	});
