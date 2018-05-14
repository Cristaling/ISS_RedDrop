(function () {
    'use strict'

    var app = angular.module('RedDrop');

    app.directive('addHospital', function () {
        return {
            scope: {},
            templateUrl: "views/directives/AddHospital.html",
            controller: function ($http, apiIP) {

                var vm = this;

                vm.tryRegisterHospital = function () {
                    $http({
                        method: 'POST',
                        url: apiIP + '/api/hospital/add',
                        data: {
                            uuid: "",
                            name : vm.hospitalName,
                            county : vm.hospitalCounty,
                            city : vm.hospitalCity
                        }
                    }).then(function (response) {
                        vm.refreshHospitalList();
                    }, function (error) { });
                }
            },
            controllerAs: "ctrl"
        };
    });
})();