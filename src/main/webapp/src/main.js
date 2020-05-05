'use strict';

angular.module('homeon')
    .controller('mainController', function ($scope, LoginLogoutSrv, $location, $rootScope, WebSocketService) {

      $scope.user = $rootScope.authDetails;

      $scope.navbar = function() {
          return { "display": 'block' };
      }
      //buscar cadastro placas, mostrar pou-up cadastrar placa. iniciar mqtt e webscoket.

      $scope.logout = function() {
            LoginLogoutSrv.logout();
            $location.path('/');
        };

        $scope.sidenavOpen = function() {
          if($rootScope.statusSidenav == true) {
            $rootScope.statusSidenav = false;
          } else {
            $rootScope.statusSidenav = true;
          }
        };



    });
