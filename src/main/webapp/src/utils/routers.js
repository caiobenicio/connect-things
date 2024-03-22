'use strict';

angular.module('homeon').config(function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: 'src/signin/signin.html',
			controller: 'signinCtrl'
		}).otherwise({
			redirectTo: '/'
		});

});
