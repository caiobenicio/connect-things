"use strict";

angular.module('homeon').controller('changePasswordCtrl', 	function($scope, $mdDialog, SERVICE_PATH, RestSrv, ngNotify) {

			var userUrl = SERVICE_PATH.PUBLIC_PATH + '/change-password';			
			$scope.disable = 'false';

			$scope.cancelar = function() {
				return $mdDialog.cancel();
			};

			$scope.changePassword = function(user) {
				if (user.password == null) {
					return;
				}
				
                RestSrv.edit(userUrl, user, function(status,data) {
                    if(status =='ok'){
                        ngNotify.set('Senha alterada com Sucesso!.', { type: 'success'});
                        return $mdDialog.cancel();
                    }else{
                    	ngNotify.set('Email não cadastrado!.', { type: 'error', duration: 5000 });
                    	return;
                    }

                });
			}

	});