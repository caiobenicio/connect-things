'use strict';

angular.module('homeon').controller('signinCtrl',
		function($scope, $rootScope, LoginLogoutSrv, $location) {
			$rootScope.navbarMenu = false;
			$rootScope.sidenavMenu = false;
			$rootScope.myAccountMenu = false;

			$scope.signin = function(email, password) {
				LoginLogoutSrv.login(email, password);
			};

			$scope.signup = function() {
				$location.path('/signup');
			};

		});
