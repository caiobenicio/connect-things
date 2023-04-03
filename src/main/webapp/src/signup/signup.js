'use strict';

angular.module('homeon').controller(
		'signupCtrl',
		function($scope, RestSrv, SERVICE_PATH, $location) {
			var userUrl = SERVICE_PATH.PUBLIC_PATH + '/signup';

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
