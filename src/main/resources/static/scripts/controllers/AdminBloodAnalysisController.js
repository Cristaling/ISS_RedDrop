(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminBloodAnalysisController', ['$location', '$cookies', '$http', '$mdDialog', function ($location, $cookies, $http, $mdDialog) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.visits = [];
        vm.bloodBagTypes = [];
        vm.bloodTypes = [];

        $http.get('/api/bloodbagtype/getall?token=' + vm.adminToken).then(function (response) {
            vm.bloodBagTypes = response.data;
        }, function (reason) {
        });

        $http.get('/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
            vm.bloodTypes = response.data;
        }, function (reason) {
        });

        vm.getBloodTypeByUUID = function (uuid) {
            for (var i in vm.bloodTypes) {
                if (vm.bloodTypes[i].uuid === uuid) {
                    return vm.bloodTypes[i].type;
                }
            }
            return "";
        };

        vm.getBloodBagTypeByUUID = function (uuid) {
            for (var i in vm.bloodBagTypes) {
                if (vm.bloodBagTypes[i].uuid === uuid) {
                    return vm.bloodBagTypes[i].name;
                }
            }
            return "";
        };

        vm.openFillAnalysisDialog = function (donationVisitToken) {
            $mdDialog.show({
                controller: function ($mdDialog, $http) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

                    vm.analysis = {};
                    vm.analysis.uuid = "";
                    vm.analysis.donationVisit = donationVisitToken;
                    vm.bloodTypes = [];

                    if (!vm.adminToken) {
                        $location.path("/admin/login");
                    }
                    $http.get('/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
                        vm.bloodTypes = response.data;
                        vm.patientBloodType = vm.bloodTypes[0];
                    }, function (reason) {

                    });

                    vm.tryRegisterAnalysis = function () {
                        $http({
                            method: 'POST',
                            url: '/api/analysisresult/add?token=' + vm.adminToken,
                            data: vm.analysis
                        }).then($mdDialog.hide(), $mdDialog.hide());
                    };
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/directives/FillAnalysisResultDirective.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshVisitList,vm.refreshVisitList);
        };

        vm.openAddBloodBagDialog=function(){
            $mdDialog.show({
                controller: 'BloodBagAddController',
                controllerAs: 'ctrl',
                templateUrl: '/views/dialogs/BloodBagAddDialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then();
        };

        vm.refreshVisitList = function () {
            $http.get('/api/donationvisit/getpendingvisits?token=' + vm.adminToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {
            });
        };

        vm.refreshVisitList();

    }]);
})();