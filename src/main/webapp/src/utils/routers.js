'use strict';

angular.module('homeon').config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'src/signin/signin.html',
		controller : 'signinCtrl'
	}).when('/home', {
		templateUrl : 'src/home/home.html',
		controller : 'homeCtrl'
	}).when('/profile', {
		templateUrl : 'src/profile/profile.html',
		controller : 'profileCtrl'
	}).when('/board', {
		templateUrl: 'src/board/board.html',
		controller: 'boardCtrl'
	}).when('/task', {
		templateUrl: 'src/task/task.html',
		controller: 'taskCtrl'
	}).otherwise({
		redirectTo : '/'
	});

});
