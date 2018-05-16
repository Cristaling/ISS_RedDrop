(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('HospitalController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        'apiIP',
        function ($location, $cookies, $http, $mdDialog, $routeParams, apiIP) {

        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.doctors = [];

        vm.addDoctor = function () {

        };

        vm.deleteDoctor = function (hospitalId) {
            $http.get(apiIP + '/api/doctor/delete?uuid=' + hospitalId).then(function (response) {
                vm.refreshDoctorList();
            }, function (reason) {

            });
        };

        vm.refreshDoctorList = function () {
            $http.get(apiIP + '/api/doctor/getall').then(function (response) {
                vm.doctors = response.data;
            }, function (reason) {

            });
        };

        vm.refreshDoctorList();

    }]);
})();