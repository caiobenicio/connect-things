	'use strict';

angular.module('homeon')
  .service('LoginLogoutSrv', function($http, $cookies, $rootScope, $location, $localStorage, ngNotify, SERVICE_PATH) {
    var serviceFactory = {};
    var urlLogin  = SERVICE_PATH.PUBLIC_PATH + '/login';
    var urlLogout = SERVICE_PATH.PUBLIC_PATH + '/logout';

    serviceFactory.login = function(email, password) {
      var requestParams = {
        method: 'GET',
        url: urlLogin,
        headers: {
          'Content-Type': 'application/json',
          'authorization' : 'Basic ' + btoa(email + ':' + password)
        }
      };

      $http(requestParams).then(
        function success(response) {
          var data = response.data;

          if (data.name) {
            $rootScope.authDetails = {name: data.name, email: data.principal.email, authenticated: data.authenticated,
            							permissions: data.authorities, id: data.principal.id };
            ngNotify.set('Logado!.', { type: 'success', duration: 5000 });
            $localStorage.authDetails = $rootScope.authDetails;
            $location.path('/home');
          } else {
            $rootScope.authDetails = { name: '', authenticated: false, permissions: [] };
            ngNotify.set('Emai ou senha não encontrado.', { type: 'error', duration: 5000 });
          }
        },
        function failure(response) {
          $rootScope.authDetails = { name: '', authenticated: false, permissions: []};
           ngNotify.set('Emai ou senha não encontrado.', { type: 'error', duration: 5000 });
        }
      );
    };

    serviceFactory.logout = function() {
      var requestParams = {
        method: 'POST',
        url: urlLogout,
        headers: { 'Content-Type': 'application/json' }
      };

      $http(requestParams).finally(function success(response) {
        delete $localStorage.authDetails;
        $rootScope.authDetails = { name: '', authenticated: false, permissions: [] };
        $rootScope.statusMenu = false;
        $location.path("/");
      });
    };

    serviceFactory.verifyAuth = function() {
      if ($localStorage.authDetails) {
        $rootScope.authDetails = $localStorage.authDetails;
      }
    };

    serviceFactory.clearStorage = function() {
        delete $localStorage.authDetails;
        delete $rootScope.authDetails;
    };

    return serviceFactory;
  });
