'use strict';

angular.module('homeon').controller('signinCtrl',
	function($scope, $rootScope, SigninSignoutSrv, $mdDialog, $mdMedia, $window) {
		angular.element($window).bind("scroll", function() {
			if (this.pageYOffset <= 150) {
				return mdDialog.cancel();
			}
		});

		$rootScope.statusMenu = false;

		$scope.user = {};
		var mdDialog = $mdDialog;

		$scope.signin = function(email, password) {
			SigninSignoutSrv.login(email, password);
		};

		$scope.addUser = function($event, user_aux) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			if (user_aux != null) {
				$scope.user = user_aux;
			}

			mdDialog.show({
				templateUrl: 'src/user/dialog/newUserDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'AddUserDialogController',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
				locals: {
					_addUser: $scope.addUser,
					_user_aux: user_aux
				}
			});
		};

		$scope.changePass = function($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));

			mdDialog.show({
				templateUrl: 'src/user/dialog/changePassword.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'changePasswordCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			});
		};
	});
