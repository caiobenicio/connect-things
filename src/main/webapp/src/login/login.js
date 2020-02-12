'use strict';

angular.module('homeon')
  .controller('loginCtrl', function($scope, $rootScope, LoginLogoutSrv) {
	  $rootScope.statusMenu = false;
	  
    $scope.login = function(email, password) {
      LoginLogoutSrv.login(email, password);
    };

  });
