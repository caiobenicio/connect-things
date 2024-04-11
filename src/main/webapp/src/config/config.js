'use strict';

angular.module('homeon').controller('configCtrl',
    function ($scope, $rootScope, $localStorage, WebSocketService) {

        $rootScope.statusMenu = true;
        $scope.user = $rootScope.authDetails;

        $scope.hasAnyPermission = function (authorities) {
            var hasPermission = false;

            $rootScope.authDetails.permissions.forEach(function (permission) {
                authorities.forEach(function (authority) {
                    if (permission.authority === authority) {
                        hasPermission = true;
                    }
                });
            });

            return hasPermission;
        };

        $scope.changeWebSocket = function() {
            $localStorage.authDetails.connectWebSocket = !$localStorage.authDetails.connectWebSocket
            $localStorage.authDetails.connectWebSocket? WebSocketService.onOpen() : WebSocketService.onClose();
		}          
    });
