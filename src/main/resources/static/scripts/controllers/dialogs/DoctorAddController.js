(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorAddController', ['$location',
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

            vm.addDoctor = function () {
                $http({
                    method: 'POST',
                    url: '/api/doctor/add?token=' + vm.adminToken,
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

        }]);
})();