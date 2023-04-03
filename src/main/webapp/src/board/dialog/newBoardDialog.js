'use strict';

angular.module('homeon').controller('newBoardDialogCtrl', 
    function($scope, RestSrv, SERVICE_PATH, $mdDialog, ngNotify, $rootScope) {
            var boardUrl = SERVICE_PATH.PRIVATE_PATH + '/board';
            $scope.board = {};

            $scope.saveBoard = function(board) {
                board.client = $rootScope.authDetails;
                RestSrv.add(boardUrl, board, function(status) {
                    if(status ==='ok'){
                        ngNotify.set('Placa Cadastrada com Sucesso!.', { type: 'success'});

                        
                        return $mdDialog.cancel();
                    }else{
                    	ngNotify.set('Placa não cadastrada!.', { type: 'error', duration: 5000 });
                    	return;
                    }
                });
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

		});
