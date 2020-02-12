'use strict';

angular.module('homeon')
  .controller('loginCtrl', function($scope, LoginLogoutSrv) {

    $scope.login = function(email, password) {
      LoginLogoutSrv.login(email, password);
    };

  });
