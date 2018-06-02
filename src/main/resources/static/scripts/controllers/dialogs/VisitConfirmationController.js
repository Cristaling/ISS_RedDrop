(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('VisitConfirmationController', ['$location',
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

            $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
                vm.bloodTypes = response.data;
                vm.bagBloodType = vm.bloodTypes[0];
            }, function (reason) {
            });

            vm.addFreshBloodBag = function (bloodTypeToken) {
                if(bloodTypeToken==null){

                }
                $http({
                    method: 'POST',
                    url: apiIP + '/api/bloodbag/add?token=' + vm.adminToken,
                    data: {
                        uuid: "",
                        donationVisit: null,
                        expireDate: null,
                        bloodBagType:"",
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