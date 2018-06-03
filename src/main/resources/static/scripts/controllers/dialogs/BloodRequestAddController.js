(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('BloodRequestAddController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        function ($location, $cookies, $http, $mdDialog, $routeParams) {

            var vm = this;

            vm.doctorToken = $cookies.get("doctorToken");

            if (!vm.doctorToken) {
                $location.path("/doctor/login");
            }

            vm.bloodTypes = [];
            vm.importances = [];
            vm.bagTypes = [];

            $http.get('/api/bloodtype/getall?token=' + vm.doctorToken).then(function (response) {
                vm.bloodTypes = response.data;
                vm.patientBloodType = vm.bloodTypes[0];
            }, function (reason) {
            });

            $http.get('/api/bloodbagtype/getall?token=' + vm.doctorToken).then(function (response) {
                vm.bagTypes = response.data;
                vm.bagType = vm.bagTypes[0];
            }, function (reason) {
            });

            $http.get('/api/utils/getimportances').then(function (response) {
                vm.importances = response.data;
                vm.patientRegisterImportance = vm.importances[0];
            }, function (reason) {
            });


            vm.addBloodRequest = function () {
                $http({
                    method: 'POST',
                    url: '/api/bloodrequest/add?token=' + vm.doctorToken,
                    data: {
                        uuid: "",
                        patientCnp: vm.patientRegisterCnp,
                        patientFullName: vm.patientRegisterFullName,
                        doctor: vm.doctorToken,
                        importance: vm.patientRegisterImportance,
                        bloodType: vm.patientBloodType.uuid,
                        bloodBagType: vm.bagType.uuid
                    }
                }).then(function () {
                    $mdDialog.hide();
                }, function (error) {
                    $mdDialog.hide();
                });
            };

        }]);
})();