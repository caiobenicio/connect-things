'use strict'

angular.module('homeon')
  .controller('homeCtrl', function($scope, $rootScope, WebSocketService){
	  
	  $rootScope.statusMenu = true;
      
	  WebSocketService.connect();

  });
