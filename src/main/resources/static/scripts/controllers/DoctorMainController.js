(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorMainController', ['$location', '$cookies','$http','$mdDialog','apiIP', function ($location, $cookies,$http,$mdDialog,apiIP) {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (!vm.doctorToken) {
            $location.path("/doctor/login");
        }

        vm.requests = [];

        vm.openAddRequestDialog = function () {
            $mdDialog.show({
                controller : function($mdDialog,$http,$cookies,$location,apiIP){
                    var vm = this;

                    vm.doctorToken = $cookies.get("doctorToken");

                    if (!vm.doctorToken) {
                        $location.path("/doctor/login");
                    }

                    vm.bloodTypes=[];

                    $http.get(apiIP + '/api/utils/getbloodtypes').then(function (response) {
                        vm.bloodTypes = response.data;
                        vm.patientBloodType=vm.bloodTypes[0];
                    }, function (reason) {

                    });

                    vm.tryRegisterBloodRequest = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/bloodrequest/add?token=' + vm.doctorToken,
                            data: {
                                uuid: "",
                                patientCnp: vm.patientRegisterCnp,
                                patientFullName: vm.patientRegisterFullName,
                                doctor : vm.doctorToken,
                                importance: vm.patientRegisterImportance,
                                type : vm.patientBloodType.type
                            }
                        }).then(function () {
                            $mdDialog.hide();
                        }, function (error) {
                            $mdDialog.hide();
                        });
                    }
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/directives/BloodRequestDirective.html',
                parent: angular.element(document.body),
                clickOutsideToClose:true
            }).then(vm.refreshRequestList,vm.refreshRequestList);
        };

        vm.deleteRequest = function () {
            $http.get(apiIP + '/api/bloodrequest/delete?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
                vm.refreshRequestList();
            }, function (reason) {

            });
        };

        vm.refreshRequestList = function () {
            $http.get(apiIP + '/api/bloodrequest/getfrom?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
                vm.requests = response.data;
            }, function (reason) {});
        };

        vm.refreshRequestList(vm.doctorToken);

    }]);
})();