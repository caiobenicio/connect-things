'use strict';

angular.module('homeon')
    .config(function ($mdIconProvider, $mdThemingProvider) {
        $mdIconProvider
            .defaultIconSet('./css/assets/svg/avatars.svg', 128)
            .icon('logo', "./css/assets/svg/logo.svg", 512)
            .icon('favicon', "./css/assets/svg/favicon.svg", 24)
            .icon('login_user_ico', "./css/assets/svg/login_user.svg", 512)
            .icon('user_home', "./css/assets/svg/user_home.svg", 512)
            .icon("google_plus", "./css/assets/svg/google_plus.svg", 512)
            .icon("hangouts", "./css/assets/svg/hangouts.svg", 512)
            .icon("twitter", "./css/assets/svg/twitter.svg", 512)
            .icon("phone", "./css/assets/svg/phone.svg", 512)
            .icon("medico_home", "./css/assets/svg/medico_home.svg", 512)
            .icon("user_f", "./css/assets/svg/sidernav/user_f.svg", 512)
            .icon("user_m", "./css/assets/svg/sidernav/user_m.svg", 512)
            .icon("user_blue", "./css/assets/svg/sidernav/user_blue.svg", 512)
            .icon("user_blue_06", "./css/assets/svg/sidernav/user_blue-06.svg", 512)
            .icon("user_tomato_07", "./css/assets/svg/sidernav/user_tomato-07.svg", 512)
            .icon("compromisso", "./css/assets/svg/sidernav/compromisso.svg", 512)
            .icon("generic_user", "./css/assets/svg/sidernav/generic_user.svg", 512)
            .icon("doutor", "./css/assets/svg/sidernav/doutor.svg", 512)
            .icon("doutora", "./css/assets/svg/sidernav/doutora.svg", 512)
            .icon("funcionario_m", "./css/assets/svg/sidernav/funcionario_m.svg", 512)
            .icon("funcionario_f", "./css/assets/svg/sidernav/funcionario_f.svg", 512)
            .icon("paciente_m", "./css/assets/svg/sidernav/paciente_m.svg", 512)
            .icon("paciente_f", "./css/assets/svg/sidernav/paciente_f.svg", 512)
            .icon("paciente_home", "./css/assets/svg/paciente_home.svg", 512)
            .icon('medico','./css/assets/svg/sidernav/medico.svg',512)
            .icon('setor','./css/assets/svg/sidernav/setor.svg',512)
            .icon('convenio','./css/assets/svg/sidernav/convenio.svg',512)
            .icon('cargo','./css/assets/svg/sidernav/cargo.svg',512)
            .icon('funcionario','./css/assets/svg/sidernav/funcionario.svg',512)
            .icon('paciente','./css/assets/svg/sidernav/paciente.svg',512)
            .icon('agenda','./css/assets/svg/sidernav/agenda.svg',512)
            .icon('escala','./css/assets/svg/sidernav/escala.svg',512)
             .icon('escala_atendimento','./css/assets/svg/sidernav/escala_atendimento.svg',512)
            .icon('prontuario','./css/assets/svg/sidernav/prontuario.svg',512)
            .icon('medicamento','./css/assets/svg/sidernav/medicamento.svg',512)
            .icon('home','./css/assets/svg/sidernav/home.svg',512)
            .icon('remedio','./css/assets/svg/sidernav/remedio.svg',512)
            .icon('notificacoes','./css/assets/svg/sidernav/notificacoes.svg',512)
            .icon('settings','./css/assets/svg/sidernav/settings.svg',512)
            .icon('menu', './css/assets/svg/menu.svg', 24)
            .icon('person','./css/assets/svg/person.svg',24)
            .icon('search','./css/assets/svg/search.svg',24)
            .icon('circle_plus','./css/assets/svg/circle_plus.svg',24)
            .icon('phone_form','./css/assets/svg/phone_form.svg',24)
            .icon('email_form','./css/assets/svg/email_form.svg',24)
            .icon('key_form','./css/assets/svg/key_form.svg',24)
            .icon('group_access_form','./css/assets/svg/group_access_form.svg',24)
            .icon('bike_icon','./css/assets/svg/bike_icon.svg',24);


            // For example: raised button text will be black instead of white.
            var whiteMap = $mdThemingProvider.extendPalette('red', {
                '500': '#ffffff',
                'contrastDefaultColor': 'dark'
            });

            $mdThemingProvider.definePalette('whiteMap', whiteMap);           

        $mdThemingProvider.theme('default')
            .primaryPalette('whiteMap')
            .accentPalette('red');

    });