(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('HospitalAddController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        function ($location, $cookies, $http, $mdDialog, $routeParams) {

            var vm = this;

            vm.adminToken = $cookies.get("adminToken");

            if (!vm.adminToken) {
                $location.path("/admin/login");
            }

            vm.addHospital = function () {
                $http({
                    method: 'POST',
                    url: '/api/hospital/add?token=' + vm.adminToken,
                    data: {
                        uuid: "",
                        name: vm.hospitalName,
                        city: vm.hospitalCity,
                        county: vm.hospitalCounty
                    }
                }).then($mdDialog.hide(), $mdDialog.hide());
            }

        }]);
})();