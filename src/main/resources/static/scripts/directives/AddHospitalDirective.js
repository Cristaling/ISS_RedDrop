(function () {
    'use strict'

    var app = angular.module('RedDrop');

    app.directive('addHospital', function () {
        return {
            scope: {},
            templateUrl: "views/directives/AddHospital.html",
            controller: function ($mdDialog, $scope, $http, apiIP) {

                var vm = this;

                vm.tryRegisterHospital = function () {
                    $http({
                        method: 'POST',
                        url: apiIP + '/api/hospital/add',
                        data: {
                            uuid: "",
                            name: vm.hospitalName,
                            city: vm.hospitalCity,
                            county: vm.hospitalCounty
                        }
                    }).then(function () {
                        $mdDialog.cancel();
                    }, function (error) {
                    });
                }
            },
            controllerAs: "ctrl"
        };
    });
})();