(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminMainController', ['$location', '$cookies', '$http', '$mdDialog', 'apiIP', function ($location, $cookies, $http, $mdDialog, apiIP) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.hospitals = [];

        vm.openRegisterDoctorDialog = function (hospitalToken) {
            $mdDialog.show({
                template: '<div register-doctor data-hospital-id="' + hospitalToken + '"></div>',
                clickOutsideToClose:true
            });
        };

        vm.openAddHospitalDialog = function () {
            $mdDialog.show({
                controller : function($mdDialog,$http,apiIP){
                    var vm = this;

                    vm.tryRegisterHospital = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/hospital/add',
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
                clickOutsideToClose:true
            }).then(vm.refreshHospitalList,vm.refreshHospitalList);
        };

        vm.deleteHospital = function (hospitalId) {
            $http.get(apiIP + '/api/hospital/delete?uuid=' + hospitalId).then(function (response) {
                vm.refreshHospitalList();
            }, function (reason) {

            });
        };

        vm.refreshHospitalList = function () {
            $http.get(apiIP + '/api/hospital/getall').then(function (response) {
                vm.hospitals = response.data;
            }, function (reason) {

            });
        };

        vm.refreshHospitalList();

    }]);
})();