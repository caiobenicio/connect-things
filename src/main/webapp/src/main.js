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


            console.log(hasPermission);
            return hasPermission;
        };
    });
