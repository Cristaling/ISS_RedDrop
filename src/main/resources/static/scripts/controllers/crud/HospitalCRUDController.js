(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('HospitalCRUDController', ['$location',
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

            vm.hospitals = [];

            vm.goToHospitalPage = function (hospitalToken) {
                $location.path("/crud/doctor/" + hospitalToken);
            }

            vm.addHospital = function () {
                $mdDialog.show({
                    controller: 'HospitalAddController',
                    controllerAs: 'ctrl',
                    templateUrl: '/views/dialogs/HospitalAddDialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(vm.refreshHospitalList, vm.refreshHospitalList);
            };

            vm.openRegisterDoctorDialog = function (hospitalToken) {
                $mdDialog.show({
                    controller: function ($mdDialog, $http) {
                        var vm = this;

                        vm.adminToken = $cookies.get("adminToken");

                        if (!vm.adminToken) {
                            $location.path("/admin/login");
                        }


                        vm.addDoctor = function () {
                            $http({
                                method: 'POST',
                                url: '/api/doctor/add?token=' + vm.adminToken,
                                data: {
                                    uuid: "",
                                    password: vm.doctorPassword,
                                    fullName: vm.nameRegister,
                                    hospital: hospitalToken,
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
                    templateUrl: '/views/dialogs/DoctorAddDialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(vm.refreshHospitalList, vm.refreshHospitalList);
            };

            vm.deleteHospital = function (hospitalId) {
                $http.get('/api/hospital/delete?token=' + vm.adminToken + '&uuid=' + hospitalId).then(function (response) {
                    vm.refreshHospitalList();
                }, function (reason) {

                });
            };

            vm.refreshHospitalList = function () {
                $http.get('/api/hospital/getall?token=' + vm.adminToken).then(function (response) {
                    vm.hospitals = response.data;
                }, function (reason) {
                });
            };

            vm.refreshHospitalList();

        }]);
})();