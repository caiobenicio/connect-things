'use strict';

angular.module('homeon').controller('mainController',
	function($scope, LoginLogoutSrv, $location, $rootScope, $localStorage, WebSocketService) {

		$scope.user = $rootScope.authDetails;
		$rootScope.statusMenu = true;
		$rootScope.profileIconName = $localStorage.profileIconName;

		$scope.WebSocketService = WebSocketService;

		$scope.hasAnyPermission = function(authorities) {
			var hasPermission = false;

			$rootScope.authDetails.permissions.forEach(function(permission) {
				authorities.forEach(function(authority) {
					if (permission.authority === authority) {
						hasPermission = true;
					}
				});
			});

			return hasPermission;
		};

		// buscar cadastro placas, mostrar pou-up cadastrar placa. iniciar
		// mqtt e webscoket.

		$scope.logout = function() {
			LoginLogoutSrv.logout();
		};

		$scope.profile = function() {
			$location.path('/profile');;
		};

		$scope.isObjectEmpty = function(obj) {
			return Object.keys(obj).length === 0;
		}

//		WebSocketService.onMessage(function(message) {
//			$scope.received = angular.fromJson(message);
//
//			angular.forEach($scope.received.pinsIn, function(value, key) {
//				var port = {
//					port: value,
//					type: "IN"
//				};
//
//				$scope.ports.push(port);
//			});
//
//			angular.forEach($scope.received.pinsOut, function(value, key) {
//				var port = {
//					port: value,
//					type: "OUT"
//				};
//
//				$scope.ports.push(port);
//			});
//
//		});
		
	});

