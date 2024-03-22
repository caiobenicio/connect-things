'use strict';

angular.module('homeon')
	.service('SigninSignoutSrv', function($http, $rootScope, $location, $localStorage, ngNotify, SERVICE_PATH) {
		var serviceFactory = {};
		var urlLogin = SERVICE_PATH.PUBLIC_PATH + '/login';
		var urlLogout = SERVICE_PATH.PUBLIC_PATH + '/logout';

		serviceFactory.login = function(email, password) {
			var requestParams = {
				method: 'GET',
				url: urlLogin,
				headers: {
					'Content-Type': 'application/json',
					'authorization': 'Basic ' + btoa(email + ':' + password)
				}
			};

			$http(requestParams).then(
				function success(response) {
					var data = response.data;

					if (data.name) {
						$rootScope.authDetails = {
							name: data.name,
							email: data.principal.email,
							authenticated: data.authenticated,
							permissions: data.authorities,
							id: data.principal.id,
							boards: []
						};

						$localStorage.authDetails = $rootScope.authDetails;

						let arrName = data.name.trim().split(/\b(\s)/);
						let firstName = arrName[0].substring(0, 1).toUpperCase();

						if (arrName.length > 1) {
							let lastName = arrName[arrName.length - 1] != undefined ? arrName[arrName.length - 1].substring(0, 1).toUpperCase() : ""
							$rootScope.profileIconName = firstName + lastName;
						} else {
							$rootScope.profileIconName = firstName;
						}

						$localStorage.profileIconName = $rootScope.profileIconName;
						$location.path('/home');
						ngNotify.set('Logado!.', { type: 'success', duration: 5000 });
					} else {
						$rootScope.authDetails = { name: '', authenticated: false, permissions: [], boards: [] };
						ngNotify.set('Emai ou senha não encontrado.', { type: 'error', duration: 5000 });
					}
				},
				function failure(response) {
					$rootScope.authDetails = { name: '', authenticated: false, permissions: [], boards: [] };
					ngNotify.set('Emai ou senha não encontrado.', { type: 'error', duration: 5000 });
				}
			);
		};

		serviceFactory.logout = function() {
			var requestParams = {
				method: 'POST',
				url: urlLogout,
				headers: { 'Content-Type': 'application/json' }
			};

			$http(requestParams).finally(function success(response) {
				delete $localStorage.authDetails;
				delete $localStorage.profileIconName;
				$rootScope.authDetails = { name: '', authenticated: false, permissions: [], boards: [] };
				$rootScope.statusMenu = false;
				$location.path("/");
			});
		};

		serviceFactory.verifyAuth = function() {
			if ($localStorage.authDetails) {
				$rootScope.authDetails = $localStorage.authDetails;
			}
		};

		return serviceFactory;
	});
