'use strict';

angular.module('homeon').controller('mainController',
	function($scope, LoginLogoutSrv, $location, $rootScope, WebSocketService, $mdSidenav, 
		$mdDialog, $mdMenu) {

		$scope.user = $rootScope.authDetails;
		$rootScope.statusMenu = true;
		var mdDialog = $mdDialog;

		let arrName = $scope.user.name.trim().split(/\b(\s)/)
		let firstName = arrName[0].substring(0, 1).toUpperCase();

		if (arrName.length > 1) {
			let lastName = arrName[arrName.length - 1] != undefined ? arrName[arrName.length - 1].substring(0, 1).toUpperCase() : ""
			$scope.profileDefault = firstName + lastName
		} else {
			$scope.profileDefault = firstName
		}

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

		$rootScope.$on('updateCurrentUserPerfil', function(event, data) {

			$rootScope.authDetails.name = data.user.nome;
			$rootScope.authDetails.permissions[0].authority = data.user.permissoes[0].role;
			//$rootScope.authDetails.file = data.user.fileUpload;

			console.log("updateCurrentUser");

		});

		$scope.navbar = function() {
			return {
				"display": 'block'
			};
		}

		$scope.toggleSideNav = function() {
			$mdSidenav('left').toggle();
		};


		// buscar cadastro placas, mostrar pou-up cadastrar placa. iniciar
		// mqtt e webscoket.

		$scope.logout = function() {
			LoginLogoutSrv.logout();
		};

		$scope.profile = function() {
			$location.path('/profile');;
		};

		$scope.hoverOut = function(){
			$mdMenu.hide();
		};

		$scope.$on("$mdMenuClose", function() { $mdMenu.hide(); });
	});

