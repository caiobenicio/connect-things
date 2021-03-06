'use strict';

var BASE_URL;
BASE_URL = window.location.origin + '/api';

angular.module('homeon', ['checklist-model', 'ngNotify', 'ngRoute', 'ngCookies', 'ngStorage', 'ngStomp', 'ui.bootstrap'])
  .constant('SERVICE_PATH', {
    'ROOT_PATH': BASE_URL,
    'PUBLIC_PATH': BASE_URL + '/public',
    'PRIVATE_PATH': BASE_URL + '/private'
  })
  .config(function($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.interceptors.push('httpRequestInterceptor');
  })
  .run(function($rootScope, ngNotify, LoginLogoutSrv, $location) {

	//LoginLogoutSrv.clearStorage();
	$rootScope.authDetails = { name: '', authenticated: false, permissions: [] };
    LoginLogoutSrv.verifyAuth();

    $rootScope.$on('$locationChangeStart', function () {

    	var url = $location.url();
        if($rootScope.authDetails.authenticated == false && url != '/signup' && url != '/confirmCreatedUser'){
            $location.path('/signin');
        }
    });
  }).run(function($rootScope) {
	  $rootScope.navbarMenu = false;
      $rootScope.sidenavMenu = false;
  });
