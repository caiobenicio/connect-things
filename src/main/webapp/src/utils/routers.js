'use strict';

angular.module('homeon')
.config(function($routeProvider) {
    $routeProvider
	    .when('/', {
	        templateUrl: 'src/home/home.html',
	        controller: 'homeCtrl'
	       })
	    .when('/login', {
	            templateUrl: 'src/login/login.html',
	            controller: 'loginCtrl'          
	       })
	    .otherwise({
	      redirectTo: '/'
	    }); 
});