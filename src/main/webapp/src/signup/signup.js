'use strict';

angular.module('homeon')
  .controller('signupCtrl', function($scope, $rootScope, LoginLogoutSrv) {
	  $rootScope.statusMenu = false;

    $scope.signup = function(username, email, password, confirmPassword) {
     // LoginLogoutSrv.login(email, password);
     console.log(username, email, password, confirmPassword);
    };

  });
