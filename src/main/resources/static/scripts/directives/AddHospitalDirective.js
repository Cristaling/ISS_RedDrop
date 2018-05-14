(function () {
    'use strict'

    var app = angular.module('RedDrop');

    app.directive('addHospital', function () {
        return {
            scope: {},
            templateUrl: "views/directives/AddHospital.html",
            controller: function ($scope, $http, apiIP) {

                var vm = this;

                vm.tryRegisterDoctor = function () {
                    $http({
                        method: 'POST',
                        url: apiIP + '/api/hospital/add',
                        data: {
                            uuid: "",
                            name : vm.hospitalName,
                            county : vm.hospitalCounty,
                            city : vm.hospitalCity
                        }
                    }).then(function (response) { }, function (error) { });
                }
            },
            controllerAs: "ctrl"
        };
    });
})();