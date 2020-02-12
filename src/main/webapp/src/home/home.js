'use strict'

angular.module('homeon')
  .controller('homeCtrl', function($scope, $rootScope){
	  
	  $rootScope.statusMenu = true;

      $scope.hasAnyPermission = function(authorities) {
          var hasPermission = false;

          $rootScope.authDetails.permissions.forEach(function(permission) {
              authorities.forEach(function(authority) {
                  if (permission.authority === authority) {
                      hasPermission = true;
                  }
              });
          });



          return hasPermission;
      };

  });
