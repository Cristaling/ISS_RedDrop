(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorMainController', ['$location', '$cookies', '$http', '$mdDialog', '$mdToast', function ($location, $cookies, $http, $mdDialog, $mdToast) {
        var vm = this;

        vm.donatorToken = $cookies.get("donatorToken");

        if (!vm.donatorToken) {
            $location.path("/donator/login");
        }

        vm.visits = [];
        vm.lastDate = "";
        vm.minDate = "";
        vm.maxDate = "";
        vm.visitDate = null;

        vm.goToAnalysisPage = function (visit) {

            if (visit.bloodBagStatus.type == "UNTESTED") {

                $mdToast.show(
                    $mdToast.simple()
                        .textContent('This blood bag was not tested yet!')
                        .position('bottom right')
                        .theme('reddrop-toast')
                        .hideDelay(1000)
                ).then(function (value) {
                }, function (reason) {
                });

                return;
            }

            $location.path("/donator/analysis/" + visit.uuid);
        };

        vm.setVisit = function (visitDate) {
            if (visitDate == null) {
                $mdToast.show(
                    $mdToast.simple()
                        .textContent('You need to select a date to set up a visit!')
                        .position('bottom right')
                        .theme('reddrop-toast')
                        .hideDelay(1000)
                ).then(function (value) {
                }, function (reason) {
                });
            } else {
                $mdDialog.show({
                    controller: function () {

                        var vm = this;

                        vm.agreed = false;

                        vm.donatorToken = $cookies.get("donatorToken");

                        if (!vm.donatorToken) {
                            $location.path("/donator/login");
                        }

                        vm.tryConsent = function () {
                            if (vm.agreed) {
                                $http({
                                    method: 'POST',
                                    url: '/api/donationvisit/add?token=' + vm.donatorToken,
                                    data: {
                                        uuid: "",
                                        donator: vm.donatorToken,
                                        date: visitDate
                                    }
                                }).then(function () {
                                    $mdToast.show(
                                        $mdToast.simple()
                                            .textContent('You have set yourself up for a visit.')
                                            .position('bottom right')
                                            .theme('reddrop-toast')
                                            .hideDelay(1000)
                                    ).then($mdDialog.hide(),$mdDialog.hide());


                                    }, function (error) {

                                    }
                                );
                            } else {
                                $mdToast.show(
                                    $mdToast.simple()
                                        .textContent('You need to agree to the terms.')
                                        .position('bottom right')
                                        .theme('reddrop-toast')
                                        .hideDelay(1000)
                                ).then(function (value) {
                                }, function (reason) {
                                });
                            }

                        };
                    },
                    controllerAs: 'ctrl',
                    templateUrl: '/views/donator/EligibilityConsentDialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(vm.getLastDonationDate,vm.getLastDonationDate);
            }
        };

        vm.refreshVisitsList = function () {
            $http.get('/api/donationvisit/getvisitedvisits?token=' + vm.donatorToken + '&uuid=' + vm.donatorToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {

            });
        };

        vm.getLastDonationDate = function () {
            vm.visitDate=undefined;
            $http.get('/api/donator/getnextvisit?token=' + vm.donatorToken).then(function (response) {
                vm.minDate = new Date(response.data);
            }, function (reason) {

            });
        };

        vm.functie = function () {
            if (agreed) {


            }

        }

        vm.getLastDonationDate();
        vm.refreshVisitsList();

    }]);
})();