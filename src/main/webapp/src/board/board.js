'use strict';

angular.module('homeon').controller('boardCtrl',
	function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope, $mdMedia, $location) {
		var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
		$scope.boards = [];
//		$scope.board = {};
		var id = $rootScope.authDetails.id;

		var boardFindId = boardUrl + '/findByClientId/' + id;
		RestSrv.find(boardFindId, function(status, data) {
			if (data != null) {
				$scope.boards = data;
				$rootScope.authDetails.boards = $scope.boards;
			}
		});

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.showDialogNewBoard = function($event) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
			$mdDialog.show({
				templateUrl: 'src/board/dialog/newBoardDialog.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				controller: 'newBoardDialogCtrl',
				controllerAs: 'ctrl',
				clickOutsideToClose: true,
				fullscreen: useFullScreen,
			})
				.then(function(data) {
					$scope.boards.push(data);
				}, function() {

				});
		};

		$scope.boardDetails = function(id) {
			$location.path('/board/' + id);
		}

	});
