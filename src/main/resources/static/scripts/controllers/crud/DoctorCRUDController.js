(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorCRUDController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        function ($location, $cookies, $http, $mdDialog, $routeParams) {

            var vm = this;

            vm.adminToken = $cookies.get("adminToken");
            vm.hospitalID = $routeParams["hospitalToken"];

            if (!vm.adminToken) {
                $location.path("/admin/login");
            }

            vm.doctors = [];

            vm.addDoctor = function () {
                $mdDialog.show({
                    controller: 'DoctorAddController',
                    controllerAs: 'ctrl',
                    templateUrl: '/views/dialogs/DoctorAddDialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(vm.refreshDoctorList, vm.refreshDoctorList);
            };

            vm.deleteDoctor = function (doctorId) {
                $http.get('/api/doctor/delete?token=' + vm.adminToken + '&uuid=' + doctorId).then(function () {
                    vm.refreshDoctorList();
                }, function (reason) {

                });
            };

            vm.refreshDoctorList = function () {
                $http.get('/api/doctor/getbyhospital?token=' + vm.adminToken + '&uuid=' + vm.hospitalID).then(function (response) {
                    vm.doctors = response.data;
                }, function (reason) {

                });
            };

            vm.refreshDoctorList();

        }]);
})();