'use strict';

angular.module('homeon').controller('automationCtrl',
    function ($scope, $rootScope, $mdColors) {

        $rootScope.statusMenu = true;
        $scope.cards = [];

        $rootScope.datepickerDate = new Date();

        var verde = $mdColors.getThemeColor('default-green-A100');
        var azul = $mdColors.getThemeColor('default-blue-100');
        var roxo = $mdColors.getThemeColor('default-DeepPurple-200');
        var rosa = $mdColors.getThemeColor('default-pink-100');

        $scope.setCards = function () {
            $scope.cards = [
                {
                    name: "Hoje",
                    description: "Agendamentos",
                    count: 2,
                    Color: verde
                },
                {
                    name: "Programados",
                    description: "Agendados",
                    count: 2,
                    Color: azul
                },
                {
                    name: "Concluidos",
                    description: "Acionado",
                    count: 1,
                    Color: roxo
                },
                {
                    name: "Todos",
                    description: "Cancelado",
                    count: 1,
                    Color: rosa
                }]
        }
        $scope.setCards();

    });
