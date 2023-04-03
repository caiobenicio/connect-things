'use strict';
angular.module('homeon').controller('mainController',
	function($scope, LoginLogoutSrv, $location, $rootScope, $mdSidenav, 
		$mdDialog, $mdMedia, $localStorage) {
		
		$scope.user = $rootScope.authDetails;
		$rootScope.statusMenu = true;
		$rootScope.profileIconName = $localStorage.profileIconName;

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

//		$scope.navbar = function() {
//			return {
//				"display": 'block'
//			};
//		}
//
//		$scope.openLeftMenu = function() {
//			$mdSidenav('left').toggle();
//		};
//
//        $scope.closeLeftMenu = function () {
//            $mdSidenav('left').close();
//
//        };
		// buscar cadastro placas, mostrar pou-up cadastrar placa. iniciar
		// mqtt e webscoket.

		$scope.logout = function() {
			LoginLogoutSrv.logout();
		};

		$scope.profile = function() {
			$location.path('/profile');;
		};
  		
		$scope.showDialogNewBoard = function ($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));	
			$mdDialog.show({
				templateUrl: 'src/board/dialog/newBoardDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newBoardDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			});

		};
		
		
	})
	
.directive('mydirect', function() {
    return {
		scope: true,
        restrict: 'E',
        replace: false,
        templateUrl: '../index.html',
    };
});

