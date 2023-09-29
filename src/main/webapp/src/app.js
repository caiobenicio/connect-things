'use strict';

var BASE_URL;
var BASE_WS_URL;
BASE_URL = window.location.origin + '/api';
BASE_WS_URL = 'ws://' + window.location.origin.replace(/https?:\/\//i, "");

angular.module('homeon', ['ngMaterial', 'ngMdIcons', 'checklist-model', 'ngNotify',
	'ngRoute', 'ngCookies', 'ngStorage', 'ui.bootstrap', 'ui.sortable', 'ngWebSocket'])//'ngStomp'
	.constant('SERVICE_PATH', {
		'ROOT_PATH': BASE_URL,
		'PUBLIC_PATH': BASE_URL + '/public',
		'PRIVATE_PATH': BASE_URL + '/private',
		'WS_PUBLIC_PATH': BASE_WS_URL + '/socket',
	})
	.config(function($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$httpProvider.defaults.withCredentials = true;
		$httpProvider.interceptors.push('httpRequestInterceptor');
	})
	.run(function($rootScope, LoginLogoutSrv, $location) {
		$(".main").height("100%");
		$rootScope.authDetails = { name: '', authenticated: false, permissions: [] };
		LoginLogoutSrv.verifyAuth();

		$rootScope.$on('$locationChangeStart', function() {
			var url = $location.url();
			if ($rootScope.authDetails.authenticated == false) {
				$rootScope.statusMenu = false;
				$location.path('/');
				$(".main").height("100%");
			}
		});
	});

