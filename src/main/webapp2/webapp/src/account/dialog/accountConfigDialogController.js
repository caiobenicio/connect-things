"use strict";

angular.module('homeon').controller('accountConfigDialogController', function($scope, $mdDialog, SERVICE_PATH, RestSrv, ngNotify) {

			var userUrl = SERVICE_PATH.PUBLIC_PATH + '/signup';			
			$scope.disable = 'false';
			$scope.user = {};
			$scope.avatars = [ 'svg-1', 'svg-2', 'svg-3', 'svg-4' ];

			$scope.cancelar = function() {
				return $mdDialog.cancel();
			};

			$scope.salvar = function(user) {
				if (user.password != user.confirmPassword) {
					$scope.disable = 'true'
					return;
				}
				
                RestSrv.add(userUrl, user, function(status,data) {

                    if(status ==='ok'){
                        ngNotify.set('Usuario Cadastrado com Sucesso!.', { type: 'success'});
                        return $mdDialog.cancel();
                    }else{
                    	ngNotify.set('Email ja cadastrado!.', { type: 'error', duration: 5000 });
                    	return;
                    }

                });
			}

	});