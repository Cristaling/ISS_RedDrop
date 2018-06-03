(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('VisitManagementController', ['$location', '$cookies', '$http', '$mdDialog', function ($location, $cookies, $http, $mdDialog) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.visits = [];
        vm.bloodStocks = [];
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

        vm.confirmVisitDialog=function (donationVisitToken) {
            $http.get('/api/donationvisit/markdone?token=' + vm.adminToken+"&donationVisitUUID="+donationVisitToken).then(function (response) {
                if(!response.data.successful) {
                    $mdDialog.show({
                        controller: function ($mdDialog, $http) {
                            var vm = this;

                            vm.adminToken = $cookies.get("adminToken");
                            vm.bloodTypes = [];

                            if (!vm.adminToken) {
                                $location.path("/admin/login");
                            }
                            $http.get('/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
                                vm.bloodTypes = response.data;
                                vm.patientBloodType = vm.bloodTypes[0];
                            }, function (reason) {

                            });


                            vm.markDone = function () {
                                $http.get('/api/donationvisit/markdone?token=' + vm.adminToken + "&donationVisitUUID=" + donationVisitToken + "&bloodTypeUUID=" + vm.patientBloodType.uuid).then(function (response) {
                                    $mdDialog.hide();
                                }, function (reason) {
                                    $mdDialog.hide();
                                });
                            };
                        },
                        controllerAs: 'ctrl',
                        templateUrl: '/views/dialogs/VisitConfirmationDialog.html',
                        parent: angular.element(document.body),
                        clickOutsideToClose: true
                    }).then(vm.refreshVisitList, vm.refreshVisitList);
                } else {
                    vm.refreshVisitList();
                }
            }, function (reason) {

            });
        };

        vm.deleteDonationVisit= function (visitId) {
            $http.get('/api/donationvisit/delete?token=' + vm.adminToken + '&uuid=' + visitId).then(function (response) {
                vm.refreshVisitList();
            }, function (reason) {
                vm.refreshVisitList();
            });
        };

        vm.refreshBloodBagStocks = function () {
            $http.get('/api/bloodbag/stock?token=' + vm.adminToken).then(function (response) {
                vm.bloodStocks = response.data;
                for (var key in vm.bloodStocks) {
                    vm.bloodStocks[key].stock = vm.convertStock(response.data[key].stock);
                }
            }, function (reason) {
            });
        };

        vm.refreshVisitList = function () {
            $http.get('/api/donationvisit/getunvisitedvisits?token=' + vm.adminToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks();
        vm.refreshVisitList();

    }]);
})();