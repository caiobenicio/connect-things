'use strict'

angular.module('homeon')
  .controller('homeCtrl', function($scope, $rootScope, WebSocketService, RestSrv){
	  
	  $rootScope.navbarMenu = true;
      $rootScope.sidenavMenu = true;
	  
//	  var isGateway = $rootScope.authDetails.isGateway;
//	  if(isGateway) {
//		  RestSrv.findByEmail('/api/private/user', $rootScope.authDetails.email, function(response) {
//		        console.log(response);
//	      });
//	  }
//      
	//  WebSocketService.connect();

  });
