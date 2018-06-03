(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AnalysisShowController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        function ($location, $cookies, $http, $mdDialog, $routeParams) {

            var vm = this;

            vm.donatorToken = $cookies.get("donatorToken");
            vm.visitToken = $routeParams["visitToken"];

            if (!vm.donatorToken) {
                $location.path("/donator/login");
            }

            vm.analysis;

            vm.getAnalysisData = function () {
                $http.get('/api/analysisresult/getbyvisit?token=' + vm.donatorToken + '&uuid=' + vm.visitToken).then(function (response) {
                    vm.analysis = response.data;
                }, function (reason) {

                });
            };

            vm.getAnalysisData();

        }]);
})();