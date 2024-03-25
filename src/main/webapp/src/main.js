'use strict';

angular.module('homeon').controller('mainController',
	function($scope, $location, $rootScope, $localStorage, SigninSignoutSrv) {

		$scope.user = $rootScope.authDetails;
		$rootScope.profileIconName = $localStorage.profileIconName;
		$rootScope.statusMenu = false;
		
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

		$scope.logout = function() {
			SigninSignoutSrv.logout();
		};

		$scope.profile = function() {
			$location.path('/profile');;
		};

		$scope.isObjectEmpty = function(obj) {
			return Object.keys(obj).length === 0;
		}
		
	});
