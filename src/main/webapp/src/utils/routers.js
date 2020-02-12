'use strict';

angular.module('homeon')
.config(function($routeProvider) {
    $routeProvider
	    .when('/', {
	        templateUrl: 'src/home/home.html',
	        controller: 'homeCtrl'
	       })
	    .when('/signin', {
            templateUrl: 'src/signin/signin.html',
            controller: 'signinCtrl'          
	       })
	    .when('/signup', {
	        templateUrl: 'src/signup/signup.html',
	        controller: 'signupCtrl'          
	       })
	    .otherwise({
	      redirectTo: '/'
	    }); 
});

