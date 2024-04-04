'use strict';

angular.module('homeon').config(function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: 'src/signin/signin.html',
			controller: 'signinCtrl'
		}).when('/home', {
			templateUrl: 'src/home/home.html',
			controller: 'homeCtrl'
		}).when('/profile', {
			templateUrl: 'src/profile/profile.html',
			controller: 'profileCtrl'
		}).when('/board', {
			templateUrl: 'src/board/board.html',
			controller: 'boardCtrl'
		}).when('/board/:id', {
			templateUrl: 'src/board/board-details.html',
			controller: 'boardDetailsCtrl'
		}).when('/device', {
			templateUrl: 'src/device/device.html',
			controller: 'deviceCtrl'
		}).when('/device/:id', {
			templateUrl: 'src/device/device-details.html',
			controller: 'deviceDetailsCtrl'
		}).when('/schedule', {
			templateUrl: 'src/schedule/schedule.html',
			controller: 'scheduleCtrl'
		}).otherwise({
			redirectTo: '/'
		});

});
