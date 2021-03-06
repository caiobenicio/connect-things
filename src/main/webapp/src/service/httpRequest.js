'use strict';

angular.module('homeon')
  .service('HttpRequestSrv', function($q, $timeout, $http, ngNotify){
    return function(url, method, data, callback){
      var requestParams = {
        method: method,
        url: url,
        headers: {'Content-type': 'application/json'},
        data: data
      };
      $http(requestParams).then(
        function successCallback(response){
          callback && callback(response.data);
        });
    };
  })
  .service('RestSrv', function(HttpRequestSrv){
    var restFactory = {};
    
    // Find all data.
    restFactory.find = function(url, callback){
      HttpRequestSrv(url, 'GET', {}, callback);
    };
    
    // Aadd a new data.
    restFactory.add = function(url, data, callback){
      HttpRequestSrv(url, 'POST', data, callback);
    };
    
    // Edit a data.
    restFactory.edit = function(url, data, callback){
      HttpRequestSrv(url, 'PUT', data, callback);
    };
    
    // Delete a data.
    restFactory.delete = function(url, data, callback){
      HttpRequestSrv(url, 'DELETE', data, callback);
    };

    // find by email a data.
    restFactory.findByEmail = function(url, data, callback){
      HttpRequestSrv(url, 'GET', data, callback);
    };
    
    return restFactory;
  })

