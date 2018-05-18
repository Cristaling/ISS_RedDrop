(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorRegisterController', ['$location','$http','$cookies','apiIP', function($location,$http,$cookies,apiIP)
    {
        var vm = this;

        vm.donatorToken = $cookies.get("donatorToken");

        if (vm.donatorToken) {
            $location.path("/donator/main");
        }

        vm.goToRegisterDonator = function () {
            $http({
                method: 'POST',
                url: apiIP + '/api/donator/register',
                data: {
                    uuid : "",
                    nume : vm.donatorSurname,
                    prenume : vm.donatorFirstName,
                    cnp : vm.donatorCNP,
                    nrTel : vm.donatorTelephone,
                    password : vm.donatorPassword,
                    city : vm.donatorCity,
                    county : vm.donatorCounty,
                    address : vm.donatorAddress,
                    bloodType : "UNKNOWN"
                }
            }).then(function (response) {
                    $location.path("/donator/login");
            }, function (error) { });
        }
    }]);
})();