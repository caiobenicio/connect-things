'use strict'

angular.module('homeon').controller('profileCtrl',
	function($scope, $rootScope, RestSrv, SERVICE_PATH) {

		var id = $rootScope.authDetails.id;

		$rootScope.statusMenu = true;
		$rootScope.navbarMenu = true;
		$rootScope.sidenavMenu = true;
		$scope.tab = 1;
		$scope.user = {};
		$scope.userCopy = {};

		var userUrl = SERVICE_PATH.PRIVATE_PATH + '/user';

		$scope.setTab = function(newTab) {
			$scope.tab = newTab;
		};

		$scope.isSet = function(tabNum) {
			return $scope.tab === tabNum;
		};

		if (id != "" && id != undefined) {
			var userFindId = userUrl + '/findById/' + id;
			RestSrv.find(userFindId, function(status, data) {
				$scope.user = data;
				$scope.userCopy = angular.copy($scope.user);
			});
		}

		$scope.cancelUser = function() {
			$scope.user = angular.copy($scope.userCopy);
		}

		$scope.deleteUser = function(user) {
			RestSrv.delete(userUrl, user, function() {

			});
		};

		$scope.saveUser = function(user) {
			if (user.id) {
				RestSrv.edit(userUrl, user, function() {
					delete user.password;
				});
			}
		};
	});
