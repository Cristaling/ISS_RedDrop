(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminMainController', ['$location', '$cookies', '$http', '$mdDialog', 'apiIP', function ($location, $cookies, $http, $mdDialog, apiIP) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.goToHospitalPage = function (hospitalToken) {
            $location.path("/hospital/" + hospitalToken);
        }

        vm.hospitals = [];
        vm.visits = [];

        vm.openRegisterDoctorDialog = function (hospitalToken) {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

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
                templateUrl: '/views/directives/DoctorRegistrationDirective.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshHospitalList, vm.refreshHospitalList);
        };

        vm.openAddHospitalDialog = function () {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

                    if (!vm.adminToken) {
                        $location.path("/admin/login");
                    }

                    vm.tryRegisterHospital = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/hospital/add?token=' + vm.adminToken,
                            data: {
                                uuid: "",
                                name: vm.hospitalName,
                                city: vm.hospitalCity,
                                county: vm.hospitalCounty
                            }
                        }).then(function () {
                            $mdDialog.hide();
                        }, function (error) {
                            $mdDialog.hide();
                        });
                    }
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/directives/AddHospital.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshHospitalList, vm.refreshHospitalList);
        };

        vm.openFillAnalysisDialog = function () {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

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
                templateUrl: '/views/directives/DoctorRegistrationDirective.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshHospitalList, vm.refreshHospitalList);
        };

        vm.deleteHospital = function (hospitalId) {
            $http.get(apiIP + '/api/hospital/delete?token=' + vm.adminToken + '&uuid=' + hospitalId).then(function (response) {
                vm.refreshHospitalList();
            }, function (reason) {

            });
        };

        vm.refreshHospitalList = function () {
            $http.get(apiIP + '/api/hospital/getall?token=' + vm.adminToken).then(function (response) {
                vm.hospitals = response.data;
            }, function (reason) {

            });
        };

        vm.refreshVisitList = function () {
            $http.get(apiIP + '/api/donationvisit/getall?token=' + vm.adminToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {

            });
        };

        vm.refreshVisitList();
        vm.refreshHospitalList();

    }]);
})();