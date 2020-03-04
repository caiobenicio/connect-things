'use strict';

angular.module('homeon')
    .controller('mainController', function ($scope, LoginLogoutSrv, $location, $rootScope, WebSocketService) {

        $scope.logout = function() {
            LoginLogoutSrv.logout();
            $location.path('/');
        };

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
        
        $scope.messages = [];
        $scope.message = "";
        $scope.max = 140;

        $scope.addMessage = function() {
        	WebSocketService.send($scope.message);
          $scope.message = "";
        };

        WebSocketService.receive().then(null, null, function(message) {
          $scope.messages.push(message);
        });

    });
