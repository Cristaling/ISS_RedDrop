(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AnalysisShowController', ['$location',
        '$cookies',
        '$http',
        '$mdDialog',
        '$routeParams',
        'apiIP',
        function ($location, $cookies, $http, $mdDialog, $routeParams, apiIP) {

            var vm = this;

            vm.donatorToken = $cookies.get("donatorToken");
            vm.visitToken = $routeParams["visitToken"];

            if (!vm.donatorToken) {
                $location.path("/donator/login");
            }

            vm.analysis;

            vm.getAnalysisData = function () {
                $http.get(apiIP + '/api/analysisresult/getbyvisit?token=' + vm.donatorToken + '&uuid=' + vm.visitToken).then(function (response) {
                    vm.analysis = response.data;
                }, function (reason) {

                });
            };

            vm.getAnalysisData();

        }]);
})();