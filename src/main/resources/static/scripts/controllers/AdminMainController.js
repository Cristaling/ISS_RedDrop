(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminMainController', ['$location', '$cookies', '$http', '$mdDialog', 'apiIP', function ($location, $cookies, $http, $mdDialog, apiIP) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.goToHospitalPage = function (hospitalToken) {
            $location.path("/crud/doctor/" + hospitalToken);
        }

        vm.hospitals = [];
        vm.visits = [];
        vm.requests = [];
        vm.bloodStocks = [];
        vm.bloodBagTypes = [];
        vm.bloodTypes = [];

        $http.get(apiIP + '/api/bloodbagtype/getall?token=' + vm.adminToken).then(function (response) {
            vm.bloodBagTypes = response.data;
        }, function (reason) {
        });

        $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
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

        vm.openRegisterDoctorDialog = function (hospitalToken) {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

                    if (!vm.adminToken) {
                        $location.path("/admin/login");
                    }


                    vm.addDoctor = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/doctor/add?token=' + vm.adminToken,
                            data: {
                                uuid: "",
                                password: vm.doctorPassword,
                                fullName: vm.nameRegister,
                                hospital: hospitalToken,
                                cnp: vm.doctorCNP
                            }
                        }).then(function (response) {
                            $mdDialog.hide();
                        }, function (error) {
                            $mdDialog.hide();
                        });
                    };
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/dialogs/DoctorAddDialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshHospitalList, vm.refreshHospitalList);
        };

        vm.openAddHospitalDialog = function () {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

                    if (!vm.adminToken) {
                        $location.path("/admin/login");
                    }

                    vm.tryRegisterHospital = function () {
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/hospital/add?token=' + vm.adminToken,
                            data: {
                                uuid: "",
                                name: vm.hospitalName,
                                city: vm.hospitalCity,
                                county: vm.hospitalCounty
                            }
                        }).then($mdDialog.hide(), $mdDialog.hide());
                    }
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/directives/AddHospital.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(vm.refreshHospitalList, vm.refreshHospitalList);
        };

        vm.openFillAnalysisDialog = function (donationVisitToken) {
            $mdDialog.show({
                controller: function ($mdDialog, $http, apiIP) {
                    var vm = this;

                    vm.adminToken = $cookies.get("adminToken");

                    vm.analysis = {};
                    vm.analysis.uuid = "";
                    vm.analysis.donationVisit = donationVisitToken;
                    vm.bloodTypes = [];

                    if (!vm.adminToken) {
                        $location.path("/admin/login");
                    }
                    $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.adminToken).then(function (response) {
                        vm.bloodTypes = response.data;
                        vm.patientBloodType = vm.bloodTypes[0];
                    }, function (reason) {

                    });

                    vm.tryRegisterAnalysis = function () {
                        vm.analysis.bloodtype = vm.patientBloodType.uuid;
                        $http({
                            method: 'POST',
                            url: apiIP + '/api/analysisresult/add?token=' + vm.adminToken,
                            data: vm.analysis
                        }).then($mdDialog.hide(), $mdDialog.hide());
                    };
                },
                controllerAs: 'ctrl',
                templateUrl: '/views/directives/FillAnalysisResultDirective.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then();
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

        vm.deleteHospital = function (hospitalId) {
            $http.get(apiIP + '/api/hospital/delete?token=' + vm.adminToken + '&uuid=' + hospitalId).then(function (response) {
                vm.refreshHospitalList();
            }, function (reason) {

            });
        };

        vm.refreshHospitalList = function () {
            $http.get(apiIP + '/api/hospital/getall?token=' + vm.adminToken).then(function (response) {
                vm.hospitals = response.data;
            }, function (reason) {
            });
        };

        vm.refreshVisitList = function () {
            $http.get(apiIP + '/api/donationvisit/getall?token=' + vm.adminToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks = function () {
            $http.get(apiIP + '/api/bloodbag/stock?token=' + vm.adminToken).then(function (response) {
                vm.bloodStocks = response.data;
                for (var key in vm.bloodStocks) {
                    vm.bloodStocks[key].stock = vm.convertStock(response.data[key].stock);
                }
            }, function (reason) {
            });
        };

        vm.refreshRequestList = function () {
            $http.get(apiIP + '/api/bloodrequest/getall?token=' + vm.adminToken).then(function (response) {
                vm.requests = response.data;
            }, function (reason) {
            });
        };

        vm.refreshBloodBagStocks();
        vm.refreshVisitList();
        vm.refreshRequestList();
        vm.refreshHospitalList();

    }]);
})();