'use strict';

angular.module('homeon')
	.service('HttpRequestSrv', function($http, $location) {
		return function(url, method, data, callback) {
			var requestParams = {
				method: method,
				url: url,
				headers: { 'Content-type': 'application/json' },
				data: data
			};
			$http(requestParams).then(
				function successCallback(response) {
					var httpStatus = 'ok';
					if (response.status === 204) {
						httpStatus = response.status;
					}
					callback(httpStatus, response.data);
				},
				function errorCallback(response) {
					if (response.message !== null) {

						console.log(response.message)
						callback('error', response.message);
						$location.path('/');
					}
				});


		};
	})
	.service('RestSrv', function(HttpRequestSrv) {
		var restFactory = {};

		// Find all data.
		restFactory.find = function(url, callback) {
			HttpRequestSrv(url, 'GET', {}, callback);
		};

		restFactory.findById = function(url, data, callback) {
			new HttpRequestSrv(url, 'GET', {}, callback);
		};

		// Add a new data.
		restFactory.add = function(url, data, callback) {
			HttpRequestSrv(url, 'POST', data, callback);
		};

		// Edit a data.
		restFactory.edit = function(url, data, callback) {
			HttpRequestSrv(url, 'PUT', data, callback);
		};

		// Delete a data.
		restFactory.delete = function(url, data, callback) {
			HttpRequestSrv(url, 'DELETE', data, callback);
		};

		// find by email a data.
		restFactory.findByEmail = function(url, data, callback) {
			HttpRequestSrv(url, 'GET', data, callback);
		};

		return restFactory;
	})

