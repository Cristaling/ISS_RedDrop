(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorCRUDController', ['$location',
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

            vm.donators = [];

            vm.deleteDonator = function (donatorId) {
                $http.get(apiIP + '/api/donator/delete?token=' + vm.adminToken + '&uuid=' + donatorId).then(function (response) {
                    vm.refreshDonatorList();
                }, function (reason) {

                });
            };

            vm.refreshDonatorList = function () {
                $http.get(apiIP + '/api/donator/getall?token=' + vm.adminToken).then(function (response) {
                    vm.donators = response.data;
                }, function (reason) {
                });
            };

            vm.refreshDonatorList();

        }]);
})();