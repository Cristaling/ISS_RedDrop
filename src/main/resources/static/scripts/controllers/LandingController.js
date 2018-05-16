(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('LandingController', ['$location', function($location)
    {
        var vm = this;

        vm.goToLoginDonator = function () {
            $location.path("/donator/login");
        };

        vm.goToLoginDoctor = function () {
            $location.path("/doctor/login");
        };

        vm.goToLoginAdmin = function () {
            $location.path("/admin/login");
        };
    }]);
})();