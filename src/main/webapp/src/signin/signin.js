'use strict';

angular.module('homeon')
  .controller('signinCtrl', function($scope, $rootScope, LoginLogoutSrv, $location) {
	  $rootScope.statusMenu = false;
	  
    $scope.signin = function(email, password) {
      LoginLogoutSrv.login(email, password);
    };
    
    $scope.signup = function() {
    	 $location.path('/signup');
   };

  });
