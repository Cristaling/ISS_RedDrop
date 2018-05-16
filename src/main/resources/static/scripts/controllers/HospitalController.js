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
        vm.hospitalID = $routeParams["hospitalToken"];

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.doctors = [];

        vm.addDoctor = function () {

        };

        vm.deleteDoctor = function (doctorId) {
            $http.get(apiIP + '/api/doctor/delete?token=' + vm.adminToken + '&uuid=' + doctorId).then(function () {
                vm.refreshDoctorList();
            }, function (reason) {

            });
        };

        vm.refreshDoctorList = function () {
            $http.get(apiIP + '/api/doctor/getbyhospital?token=' + vm.adminToken + '&uuid=' + hospitalID).then(function (response) {
                vm.doctors = response.data;
            }, function (reason) {

            });
        };

        vm.refreshDoctorList();

    }]);
})();