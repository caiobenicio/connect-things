'use strict'

angular.module('homeon').controller('profileCtrl',
		function($scope, $rootScope, WebSocketService, RestSrv) {

			$rootScope.navbarMenu = true;
			$rootScope.sidenavMenu = true;
			$scope.tab = 1;
			//var findConvenioMedicoUrl = SERVICE_PATH.PRIVATE_PATH + "/convenio/findByMedico/" + $scope.medico.id;

			$scope.setTab = function(newTab) {
				$scope.tab = newTab;
			};

			$scope.isSet = function(tabNum) {
				return $scope.tab === tabNum;
			};

//			RestSrv.find("", function(status, data) {
//				
//				
//			});
		});
