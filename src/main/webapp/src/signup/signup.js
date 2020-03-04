'use strict';

angular.module('homeon').controller('signupCtrl',
	function($scope, $rootScope, $location, LoginLogoutSrv) {
		$rootScope.statusMenu = false;

		$scope.user = {};

		$scope.signup = function(user) {
			console.log(user);
			$location.path('/confirmCreatedUser');
		};

	});
angular.module('homeon')
  .controller('signupCtrl', function($scope, RestSrv, SERVICE_PATH, LoginLogoutSrv, $location, $timeout) {    
    var userUrl = SERVICE_PATH.PUBLIC_PATH + '/signup';

    $scope.disable = 'false';
    $scope.user = {}; 
    
    $scope.signup = function(user) {
      
      if(user.password != user.confirmPassword) {
        $scope.disable = 'true'        
        return;
      }
      delete user.confirmPassword;
      RestSrv.add(userUrl, user, function() {
        $location.path('/confirmCreatedUser');
       // document.getElementById("modal").onclick();
       // document.querySelector('#modal').click();        
      });
    };

    $scope.signin = function() {
      $location.path('/signin');
    }
    
  });

