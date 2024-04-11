'use strict';

var BASE_URL;
var BASE_WS_URL;
BASE_URL = window.location.origin + '/api';
BASE_WS_URL = 'ws://' + window.location.origin.replace(/https?:\/\//i, "");

angular.module('homeon', ['ngRoute', 'ngCookies', 'ngStorage', 'ngMaterial', 'ngNotify', 'ngMdIcons', 'ngWebSocket'])
	.constant('SERVICE_PATH', {
		'ROOT_PATH': BASE_URL,
		'PUBLIC_PATH': BASE_URL + '/public',
		'PRIVATE_PATH': BASE_URL + '/private',
		'WS_PATH': BASE_WS_URL + '/socket',
	})
	.config(function ($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$httpProvider.defaults.withCredentials = true;
		$httpProvider.interceptors.push('httpRequestInterceptor');
	})
	.run(function ($rootScope, $location, SigninSignoutSrv) {
		$rootScope.authDetails = { name: '', authenticated: false, permissions: [], boards: [], connectWebSocket: false };
		SigninSignoutSrv.verifyAuth();

		$rootScope.$on('$locationChangeStart', function () {
			if ($rootScope.authDetails.authenticated == false) {
				$rootScope.statusMenu = true;
				$location.path('/');
			}
		});
	});

