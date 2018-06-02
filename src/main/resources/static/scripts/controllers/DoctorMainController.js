(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorMainController', ['$location', '$cookies', '$http', '$mdDialog', 'apiIP', function ($location, $cookies, $http, $mdDialog, apiIP) {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (!vm.doctorToken) {
            $location.path("/doctor/login");
        }

        vm.requests = [];
        vm.bloodTypes = [];
        vm.bloodBagTypes = [];

        $http.get(apiIP + '/api/bloodbagtype/getall?token=' + vm.doctorToken).then(function (response) {
            vm.bloodBagTypes = response.data;
        }, function (reason) {
        });

        $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.doctorToken).then(function (response) {
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

        vm.convertStock = function (stock) {
            var result = [];
            for (var bagStock in stock) {
                result.push({
                    uuid: bagStock,
                    name: vm.getBloodBagTypeByUUID(bagStock),
                    value: stock[bagStock]
                });
            }
            return result;
        };

        vm.openAddRequestDialog = function () {
            $mdDialog.show({
                controller: 'BloodRequestAddController',
                controllerAs: 'ctrl',
                templateUrl: '/views/dialogs/BloodRequestAddDialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshRequestList, vm.refreshRequestList);
        };

        vm.deleteRequest = function (requestID) {
            $http.get(apiIP + '/api/bloodrequest/delete?token=' + vm.doctorToken + '&uuid=' + requestID).then(function (response) {
                vm.refreshRequestList();
            }, function (reason) {

            });
        };

        vm.refreshRequestList = function () {
            $http.get(apiIP + '/api/bloodrequest/getfrom?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
                vm.requests = response.data;
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks = function () {
            $http.get(apiIP + '/api/bloodbag/stock?token=' + vm.doctorToken).then(function (response) {
                vm.bloodStocks = response.data;
                for (var key in vm.bloodStocks) {
                    vm.bloodStocks[key].stock = vm.convertStock(response.data[key].stock);
                }
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks();
        vm.refreshRequestList();

    }]);
})();