'use strict';

angular.module('homeon').controller('signupCtrl',
	function($scope, $rootScope, $location, LoginLogoutSrv) {
		$rootScope.statusMenu = false;

		$scope.user = {};

		$scope.signup = function(user) {
			console.log(user);
			$location.path('/confirmCreatedUser');
		};

	});
