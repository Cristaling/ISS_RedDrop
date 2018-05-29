(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('BloodBagAddController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        'apiIP',
        function ($location, $cookies, $http, $mdDialog, $routeParams, apiIP) {

            var vm = this;

            vm.adminToken = $cookies.get("adminToken");

            if (!vm.adminToken) {
                $location.path("/admin/login");
            }

            vm.bloodTypes = [];
            vm.bagTypes = [];

            $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
                vm.bloodTypes = response.data;
                vm.bagBloodType = vm.bloodTypes[0];
            }, function (reason) {
            });

            $http.get(apiIP + '/api/bloodbagtype/getall?token=' + vm.adminToken).then(function (response) {
                vm.bagTypes = response.data;
                vm.bagType = vm.bagTypes[0];
            }, function (reason) {
            });

            vm.tryRegisterBag = function () {
                $http({
                    method: 'POST',
                    url: apiIP + '/api/bloodbag/add?token=' + vm.adminToken,
                    data: {
                        uuid: "",
                        donationVisit: null,
                        expireDate: null,
                        bloodBagType: vm.bagType.uuid,
                        bloodType: vm.bagBloodType.uuid
                    }
                }).then(function () {
                    $mdDialog.hide();
                }, function (error) {
                    $mdDialog.hide();
                });
            };

        }]);
})();