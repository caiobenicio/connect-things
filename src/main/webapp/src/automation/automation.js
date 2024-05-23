'use strict';

angular.module('homeon').controller('automationCtrl',
    function ($scope, $rootScope, $mdColors) {

        $rootScope.statusMenu = true;
        $scope.cards = [];

        this.datepickerDate = new Date();

        var verde = $mdColors.getThemeColor('default-green-A100');
        var azul = $mdColors.getThemeColor('default-blue-100');
        var roxo = $mdColors.getThemeColor('default-DeepPurple-200');
        var rosa = $mdColors.getThemeColor('default-pink-100');

        $scope.setCards = function () {
            $scope.cards = [
                {
                    name: "Agendamentos",
                    description: "Agendados",
                    count: 2,
                    Color: verde
                },
                {
                    name: "Dispositivos",
                    description: "Acionado",
                    count: 1,
                    Color: azul
                },
                {
                    name: "Dispositivos",
                    description: "Cancelado",
                    count: 1,
                    Color: roxo
                },
                {
                    name: "Dispositivos",
                    description: "Bloqueados",
                    count: 0,
                    Color: rosa
                }]
        }
        $scope.setCards();

    });
