'use strict';

angular.module('homeon')
  .controller('signupCtrl', function($scope, $rootScope, LoginLogoutSrv) {
	  $rootScope.statusMenu = false;
	  
    $scope.signup = function() {
    	$location.path('/signup');
    };

  });
