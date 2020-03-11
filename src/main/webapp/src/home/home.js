'use strict'

angular.module('homeon')
  .controller('homeCtrl', function($scope, $rootScope, WebSocketService){
	  
	  $rootScope.statusMenu = true;

      
	  WebSocketService.connect();
      
      $scope.messages = [];
      $scope.message = "";
      $scope.max = 140;

      $scope.addMessage = function() {
      	WebSocketService.send($scope.message);
        $scope.message = "";
      };

//      WebSocketService.receive().then(null, null, function(message) {
//        $scope.messages.push(message);
//      });


  });
