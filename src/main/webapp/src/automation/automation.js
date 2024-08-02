'use strict';

angular.module('homeon').controller('automationCtrl',
    function ($scope, $rootScope, $mdColors, SERVICE_PATH, RestSrv) {

        $rootScope.statusMenu = true;
        $scope.cards = [{ name: "Hoje", description: "Agendamentos", count: 0, Color: verde },{ name: "Programados", description: "Agendados", count: 0, Color: azul },{ name: "Todos", description: "Cancelado", count: 0, Color: rosa }];
		$scope.schedule = [];
		$scope.scheduled = [];
		$scope.scheduleNotEx = [];
		$scope.dateSchedule = [];
        $scope.datepickerDate = new Date();

        var verde = $mdColors.getThemeColor('default-green-A100');
        var azul = $mdColors.getThemeColor('default-blue-100');
        var roxo = $mdColors.getThemeColor('default-DeepPurple-200');
        var rosa = $mdColors.getThemeColor('default-pink-100');        

        var scheduleUrl = SERVICE_PATH.PRIVATE_PATH + '/schedule';
        var id = $rootScope.authDetails.id;

		var scheduleOpenToday = scheduleUrl + '/searchOpenSchedule/'+id;
		RestSrv.find(scheduleOpenToday, function(status, data) {
			if (data != null) {
				$scope.schedule = data;                
                $scope.cards.splice(0,1, { name: "Hoje", description: "Agendamentos", count: $scope.schedule.length, Color: verde });
			}
		});      
        
        
		var findByScheduled = scheduleUrl + '/findByScheduled/'+id;
		RestSrv.find(findByScheduled, function(status, data) {
			if (data != null) {
				$scope.scheduled = data;

                $scope.cards.splice(1,2, { name: "Programados", description: "Agendados", count: $scope.scheduled.length, Color: azul });
			}
		});     
        
		var findByAllScheduleNotEx = scheduleUrl + '/findByAllScheduleNotEx/'+id;
		RestSrv.find(findByAllScheduleNotEx, function(status, data) {
			if (data != null) {
				$scope.scheduleNotEx = data;

                $scope.cards.splice(2,3, { name: "Todos", description: "Cancelado", count: $scope.scheduleNotEx.length, Color: rosa });
			}
		});      
        
		$scope.buscaAgenda = function(date) {
            var findByDateSchedule = scheduleUrl + '/findByDateSchedule/'+id+"/"+date.toISOString().slice(0, 10);
            RestSrv.find(findByDateSchedule, function(status, data) {
                if (data != null) {
                    $scope.dateSchedule = data;
                }
            });  
		};

		$scope.buscaAgenda($scope.datepickerDate);

    });
