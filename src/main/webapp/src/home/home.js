'use strict'

angular.module('homeon')
  .controller('homeCtrl', function($scope, $rootScope, $mdDialog, $mdToast, WebSocketService, RestSrv, SERVICE_PATH) {
	  
	  $rootScope.statusMenu = true;
	  var homeUrl = SERVICE_PATH.PUBLIC_PATH + '/home';
        
      $scope.hasAnyPermission = function(authorities) {
          var hasPermission = false;

          $rootScope.authDetails.permissions.forEach(function(permission) {
              authorities.forEach(function(authority) {
                  if (permission.authority === authority) {
                      hasPermission = true;
                  }
              });
          });



          return hasPermission;
      };
      
		$scope.addHome = function() {
			console.log("caiu");
			//RestSrv.add(homeUrl, )
		}
        
        
        

//	  var isGateway = $rootScope.authDetails.isGateway;
//	  if(isGateway) {
//		  RestSrv.findByEmail('/api/private/user', $rootScope.authDetails.email, function(response) {
//		        console.log(response);
//	      });
//	  }
//      
	//  WebSocketService.connect();

  });
