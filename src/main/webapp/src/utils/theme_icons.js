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
            .icon('bike_icon','./css/assets/svg/bike_icon.svg',24)
            .icon('close','./css/assets/svg/close.svg',128)
            .icon('green_circle','./css/assets/svg/green_circle.svg',200)
            .icon('circle_white','./css/assets/svg/circle_white.svg',200)
            .icon('red_circle','./assets/svg/red_circle.svg',180);//200);

            $mdThemingProvider.definePalette('amazingDarkPaletteName', {
                '50': '343a40',
                '100': 'ffcdd2',
                '200': 'ef9a9a',
                '300': 'e57373',
                '400': 'ef5350',
                '500': 'f44336',
                '600': 'e53935',
                '700': 'd32f2f',
                '800': 'c62828',
                '900': 'b71c1c',
                'A100': 'ff8a80',
                'A200': 'ff5252',
                'A400': 'ff1744',
                'A700': 'd50000',
                // By default, text (contrast) on this palette should be white with 87% opacity.
                'contrastDefaultColor': 'light',
                // By default, for these lighter hues, text (contrast) should be 'dark'.
                'contrastDarkColors': '50 100 200 300 400 500 600 A100 A200 A400',
                // By default, for these darker hues, text (contrast) should be white with 100% opacity.
                'contrastStrongLightColors': '700 800 900 A700'
              });        

            $mdThemingProvider.theme('default')
                //.primaryPalette('amazingDarkPaletteName')
                .dark();
                // .primaryPalette('whiteMap')
                // .accentPalette('blue');

    });