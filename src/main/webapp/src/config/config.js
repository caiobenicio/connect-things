'use strict';

angular.module('homeon').controller('configCtrl',
    function ($scope, $rootScope, $localStorage, WebSocketService) {

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
            $localStorage.authDetails.websocket = true;
            $rootScope.connectWS = true;
		}

        $scope.desconectWebsocket = function() {
			WebSocketService.onClose();
            $localStorage.authDetails.websocket = false;
            $rootScope.connectWS = false;
		}
    });
