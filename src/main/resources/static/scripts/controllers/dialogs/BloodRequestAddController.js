(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('BloodRequestAddController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        'apiIP',
        function ($location, $cookies, $http, $mdDialog, $routeParams, apiIP) {

            var vm = this;

            vm.doctorToken = $cookies.get("doctorToken");

            if (!vm.doctorToken) {
                $location.path("/doctor/login");
            }

            vm.bloodTypes = [];

            $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.doctorToken).then(function (response) {
                vm.bloodTypes = response.data;
                vm.patientBloodType = vm.bloodTypes[0];
            }, function (reason) {

            });

            vm.addBloodRequest = function () {
                $http({
                    method: 'POST',
                    url: apiIP + '/api/bloodrequest/add?token=' + vm.doctorToken,
                    data: {
                        uuid: "",
                        patientCnp: vm.patientRegisterCnp,
                        patientFullName: vm.patientRegisterFullName,
                        doctor: vm.doctorToken,
                        importance: vm.patientRegisterImportance,
                        type: vm.patientBloodType.uuid
                    }
                }).then(function () {
                    $mdDialog.hide();
                }, function (error) {
                    $mdDialog.hide();
                });
            };

        }]);
})();