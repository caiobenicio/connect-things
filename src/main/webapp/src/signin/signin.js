'use strict';

angular.module('homeon').controller('signinCtrl',
	function($scope, $rootScope, LoginLogoutSrv, $location, WebSocketService) {
		$rootScope.statusMenu = false;

		$scope.signin = function(email, password) {
			LoginLogoutSrv.login(email, password);
		};

		$scope.signup = function() {
			$location.path('/signup');
		};

		
        $scope.messages = [];
        $scope.message = "caio";
        $scope.max = 140;

        $scope.addMessage = function() {
        	WebSocketService.send($scope.message);
          $scope.message = "";
        };

        WebSocketService.receive().then(null, null, function(message) {
        	console.log(message);
          $scope.messages.push(message);
        });

	});
