'use strict';

angular.module('homeon').config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'src/home/home.html',
		controller : 'homeCtrl'
	}).when('/signin', {
		templateUrl : 'src/signin/signin.html',
		controller : 'signinCtrl'
	}).when('/signup', {
		templateUrl : 'src/signup/signup.html',
		controller : 'signupCtrl'
	}).when('/confirmCreatedUser', {
		templateUrl : 'src/signup/confirmCreatedUser.html'
	}).when('/profile', {
		templateUrl : 'src/profile/profile.html',
		controller : 'profileCtrl'
	}).otherwise({
		redirectTo : '/'
	});

});
