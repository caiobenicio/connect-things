'use strict';

angular.module('homeon')
  .controller('signupCtrl', function($scope, $rootScope, LoginLogoutSrv) {
	  $rootScope.statusMenu = false;

    $scope.signup = function(username, email, password, confirmPassword) {
    	
    	if(password != confirmPassword) {
    		console.log("senhas nao conferem");
    	}
    };

  });
