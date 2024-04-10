'use strict';

angular.module('homeon').controller('configCtrl',
    function ($scope, $rootScope, WebSocketService) {

        $rootScope.statusMenu = true;
        $scope.user = $rootScope.authDetails;
        $scope.MyData = WebSocketService;

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

        $scope.conectWebsocket = function() {
			WebSocketService.onOpen();
		}

        $scope.desconectWebsocket = function() {
			WebSocketService.onClose();
		}
    });
