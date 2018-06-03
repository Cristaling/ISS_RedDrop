(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorMainController', ['$location', '$cookies', '$http', '$mdDialog', '$mdToast', 'apiIP', function ($location, $cookies, $http, $mdDialog, $mdToast, apiIP) {
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
                                    url: apiIP + '/api/donationvisit/add?token=' + vm.donatorToken,
                                    data: {
                                        uuid: "",
                                        donator: vm.donatorToken,
                                        date: visitDate
                                    }
                                }).then(function () {
                                        $mdDialog.show(
                                            $mdDialog.alert()
                                                .clickOutsideToClose(true)
                                                .textContent('You have set yourself up for a visit.')
                                                .ariaLabel('Alert Dialog Demo')
                                                .ok('Thank you!')
                                        );

                                    }, function (error) {

                                    }
                                );
                            } else {
                                $mdDialog.show(
                                    $mdDialog.alert()
                                        .clickOutsideToClose(true)
                                        .textContent('You need to give your consent to set up a visit.')
                                        .ariaLabel('Alert Dialog Demo')
                                        .ok('Got it!')
                                );
                            }

                        };
                    },
                    controllerAs: 'ctrl',
                    templateUrl: '/views/donator/EligibilityConsentDialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then();
            }
        };

        vm.refreshVisitsList = function () {
            $http.get(apiIP + '/api/donationvisit/getvisitedvisits?token=' + vm.donatorToken + '&uuid=' + vm.donatorToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {

            });
        };

        vm.getLastDonationDate = function () {
            $http.get(apiIP + '/api/donator/getnextvisit?token=' + vm.donatorToken).then(function (response) {
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