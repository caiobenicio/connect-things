'use strict';

angular.module('homeon').controller(
	'taskCtrl',
	function($scope, RestSrv, SERVICE_PATH, $location) {
		var userUrl = SERVICE_PATH.PUBLIC_PATH + '/task';

		$scope.disable = 'false';
		$scope.user = {};

		$scope.signup = function(user) {

			if (user.password != user.confirmPassword) {
				$scope.disable = 'true'
				return;
			}

			delete user.confirmPassword;
			RestSrv.add(userUrl, user, function() {
				$location.path('/');
			});
		};

	});
