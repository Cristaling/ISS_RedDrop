(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorMainController', ['$location', '$cookies','$http','apiIP', function ($location, $cookies,$http,apiIP) {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (!vm.doctorToken) {
            $location.path("/doctor/login");
        }

        vm.requests = [];

        vm.openAddRequestDialog = function () {
            $mdDialog.show({
                controller : function($mdDialog,$http,apiIP){
                    var vm = this;

                    vm.tryRegisterBloodRequest = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/bloodrequest/add',
                            data: {
                                uuid: "",
                                patientCnp: vm.patientRegisterCnp,
                                patientFullName: vm.patientRegisterFullName,
                                doctor : vm.doctorToken,
                                importance: vm.patientRegisterImportance
                            }
                        }).then(function () {
                            $mdDialog.hide();
                        }, function (error) {
                            $mdDialog.hide();
                        });
                    }
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/DoctorMainPage.html',
                parent: angular.element(document.body),
                clickOutsideToClose:true
            }).then(vm.refreshRequestList,vm.refreshRequestList);
        };

        vm.deleteRequest = function (doctorId) {
            $http.get(apiIP + '/api/bloodrequest/delete?uuid=' + doctorId).then(function (response) {
                vm.refreshRequestList();
            }, function (reason) {

            });
        };

        vm.refreshRequestList = function (doctorId) {
            $http.get(apiIP + '/api/bloodrequest/getfrom?uuid=' + doctorId).then(function (response) {
                vm.hospitals = response.data;
            }, function (reason) {

            });
        };
        vm.refreshRequestList(vm.doctorToken);

    }]);
})();