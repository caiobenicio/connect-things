'use strict';

angular.module('homeon')
  .controller('signupCtrl', function($scope, RestSrv, SERVICE_PATH, LoginLogoutSrv, $location, $timeout) {    
    var userUrl = SERVICE_PATH.PUBLIC_PATH + '/signup';

    $scope.disable = 'false';
    $scope.user = {}; 
    
    $scope.signup = function(user) {
      
      if(user.password != user.confirmPassword) {
        $scope.disable = 'true'        
        return;
      }
      
      if(user.isGateway == null || user.isGateway == undefined) {
    	  user.isGateway = false;
      }
      
      delete user.confirmPassword;
      RestSrv.add(userUrl, user, function() {
        $location.path('/confirmCreatedUser');
      });
    };

    $scope.signin = function() {
      $location.path('/signin');
    }
    
  });

