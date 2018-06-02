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
        vm.lastDate= "";
        vm.minDate="";
        vm.maxDate="";

        vm.goToAnalysisPage = function (visit) {

            if (visit.bloodBagStatus.type == "UNTESTED") {

                $mdToast.show(
                    $mdToast.simple()
                        .textContent('This blood bag was not tested yet!')
                        .position('top right')
                        .hideDelay(3000)
                ).then(function (value) {  }, function (reason) {  });

                return;
            }

            $location.path("/donator/analysis/" + visit.uuid);
        };

        vm.setVisit = function () {
            $http({
                method: 'POST',
                url: apiIP + '/api/donationvisit/add?token=' + vm.donatorToken,
                data: {
                    uuid: "",
                    donator: vm.donatorToken,
                    date: vm.visitDate
                }
            }).then(function (response) {
                $mdDialog.show(
                    $mdDialog.alert()
                        .clickOutsideToClose(true)
                        .title('Success')
                        .textContent('You successfully scheduled a visit.')
                        .ariaLabel('Success Dialog')
                        .ok('Done')
                ).then(vm.getLastDonationDate,vm.getLastDonationDate);
            }, function (error) {
            });
        };

        vm.refreshVisitsList = function () {
            $http.get(apiIP + '/api/donationvisit/getvisitedvisits?token=' + vm.donatorToken + '&uuid=' + vm.donatorToken).then(function (response) {
                vm.visits = response.data;
            }, function (reason) {

            });
        };

        vm.getLastDonationDate = function () {
            $http.get(apiIP + '/api/donator/getnextvisit?token='+vm.donatorToken).then(function (response) {
                vm.minDate = new Date(response.data);
            }, function (reason) {

            });
        };

        vm.getLastDonationDate();
        vm.refreshVisitsList();

    }]);
})();