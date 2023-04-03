'use strict';

var BASE_URL;
BASE_URL = window.location.origin + '/api';

angular.module('homeon', ['ngMaterial', 'ngMdIcons', 'checklist-model', 'ngNotify', 
                'ngRoute', 'ngCookies', 'ngStorage', 'ui.bootstrap', 'ui.sortable'])//'ngStomp'
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
  .run(function($rootScope, LoginLogoutSrv, $location) {
  	$rootScope.authDetails = { name: '', authenticated: false, permissions: [] };
    LoginLogoutSrv.verifyAuth();

    $rootScope.$on('$locationChangeStart', function () {
    	var url = $location.url();
        if($rootScope.authDetails.authenticated == false){
        	$rootScope.statusMenu = false;
        	$location.path('/');
        	$(".main").height("100%");
        }
    });
  }).run(function($rootScope) {
	    	$rootScope.status = "status_off";
        $rootScope.profileIconName = "";


  });

