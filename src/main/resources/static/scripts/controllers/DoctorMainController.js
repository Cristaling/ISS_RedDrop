(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorMainController', ['$location', '$cookies', '$http', '$mdDialog', function ($location, $cookies, $http, $mdDialog) {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (!vm.doctorToken) {
            $location.path("/doctor/login");
        }

        vm.requests = [];
        vm.bloodTypes = [];
        vm.bloodBagTypes = [];
        vm.firstTime = false;

        $http.get('/api/bloodbagtype/getall?token=' + vm.doctorToken).then(function (response) {
            vm.bloodBagTypes = response.data;
        }, function (reason) {
        });

        $http.get('/api/bloodtype/getall?token=' + vm.doctorToken).then(function (response) {
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
            $http.get('/api/bloodrequest/delete?token=' + vm.doctorToken + '&uuid=' + requestID).then(function (response) {
                vm.refreshRequestList();
            }, function (reason) {

            });
        };

        vm.refreshRequestList = function () {
            $http.get('/api/bloodrequest/getfrom?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
                vm.requests = response.data;
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks = function () {
            $http.get('/api/bloodbag/stock?token=' + vm.doctorToken).then(function (response) {
                vm.bloodStocks = response.data;
                for (var key in vm.bloodStocks) {
                    vm.bloodStocks[key].stock = vm.convertStock(response.data[key].stock);
                }
            }, function (reason) {
            });
        };

        vm.checkFirstTime = function () {

            $http.get('/api/doctor/checkfirst?token=' + vm.doctorToken).then(function (response) {
                $mdDialog.show({
                    controller: function () {

                        var vm = this;

                        vm.agreed = false;
                        vm.password1 = "a";
                        vm.password2 = "f";

                        vm.doctorToken = $cookies.get("doctorToken");

                        if (!vm.doctorToken) {
                            $location.path("/doctor/login");
                        }

                        vm.changePassword = function () {
                            $http({
                                method: 'POST',
                                url: '/api/doctor/changepassword?token=' + vm.doctorToken,
                                data: {
                                    password1: vm.password1,
                                    password2: vm.password2
                                }
                            }).then(function (response) {
                                    if (response.data) {
                                        $mdDialog.show(
                                            $mdDialog.alert()
                                                .clickOutsideToClose(true)
                                                .textContent('You have changed your password.')
                                                .ariaLabel('Alert Dialog Demo')
                                                .ok('Thank you!')
                                        );
                                    } else {
                                        $mdDialog.show(
                                            $mdDialog.alert()
                                                .clickOutsideToClose(true)
                                                .textContent('Passwords dont match.')
                                                .ariaLabel('Alert Dialog Demo')
                                                .ok('Try again!')
                                        );
                                    }

                                }, function (error) {

                                }
                            );

                        };
                    },
                    controllerAs: 'ctrl',
                    templateUrl: '/views/doctor/DoctorChangePassword.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then();
            }, function (reason) {
            });
            if (vm.firstTime.successful) {

            }

        }

        vm.checkFirstTime();
        vm.refreshBloodBagStocks();
        vm.refreshRequestList();

    }]);
})();