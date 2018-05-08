(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('LandingController', ['$location', function($location)
    {
        var vm = this;

        vm.goToLoginDonator = function () {
            $location.path("/donator/login");
        }

        vm.goToLoginMedic = function () {
            $location.path("/medic/login");
        }

    }]);
})();