(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorMainController', ['$location', '$cookies','$http','apiIP', function($location, $cookies,$http,apiIP)
    {
        var vm = this;

        vm.donatorToken = $cookies.get("donatorToken");

        if (!vm.donatorToken) {
            $location.path("/donator/login");
        }

        vm.setVisit=function(){
            $http({
                method: 'POST',
                url: apiIP + '/api/donationvisit/add?token=' + vm.donatorToken,
                data: {
                    uuid: "",
                    donator: vm.donatorToken,
                    date: vm.visitDate
                }
            }).then(function () {
                $mdDialog.hide();
            }, function (error) {
                $mdDialog.hide();
            });
        }

    }]);
})();