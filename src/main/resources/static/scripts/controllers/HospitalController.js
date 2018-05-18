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
                $mdDialog.show({
                    controller: function ($mdDialog, $http, $routeParams, apiIP) {
                        var vm = this;

                        vm.adminToken = $cookies.get("adminToken");
                        vm.hospitalID = $routeParams["hospitalToken"];

                        if (!vm.adminToken) {
                            $location.path("/admin/login");
                        }

                        vm.tryRegisterDoctor = function () {
                            $http({
                                method: 'POST',
                                url: apiIP + '/api/doctor/add?token=' + vm.adminToken,
                                data: {
                                    uuid: "",
                                    password: vm.doctorPassword,
                                    fullName: vm.nameRegister,
                                    hospital: vm.hospitalID,
                                    cnp: vm.doctorCNP
                                }
                            }).then(function (response) {
                                $mdDialog.hide();
                            }, function (error) {
                                $mdDialog.hide();
                            });
                        };
                    },
                    controllerAs: 'ctrl',
                    templateUrl: '/views/directives/DoctorRegistrationDirective.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(vm.refreshDoctorList, vm.refreshDoctorList);
            };

            vm.deleteDoctor = function (doctorId) {
                $http.get(apiIP + '/api/doctor/delete?token=' + vm.adminToken + '&uuid=' + doctorId).then(function () {
                    vm.refreshDoctorList();
                }, function (reason) {

                });
            };

            vm.refreshDoctorList = function () {
                $http.get(apiIP + '/api/doctor/getbyhospital?token=' + vm.adminToken + '&uuid=' + vm.hospitalID).then(function (response) {
                    vm.doctors = response.data;
                }, function (reason) {

                });
            };

            vm.refreshDoctorList();

        }]);
})();