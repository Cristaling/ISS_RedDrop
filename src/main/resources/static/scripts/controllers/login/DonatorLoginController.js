(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorLoginController', ['$location', '$cookies', '$http', 'apiIP', function($location, $cookies, $http, apiIP)
    {
        var vm = this;

        vm.donatorToken = $cookies.get("donatorToken");

        if (vm.donatorToken) {
            $location.path("/donator/main");
        }

        vm.goToRegisterDonator = function () {
            $location.path("/donator/register");
        }

        vm.tryLoginDonator = function () {
            $http({
                method: 'POST',
                url: apiIP + '/api/donator/login',
                data: {
                    cnp: vm.donatorCNP,
                    password: vm.donatorPassword
                }
            }).then(function (response) {
                if (response.data.succesful) {
                    $cookies.put("donatorToken", response.data.token);
                    $location.path("/donator/main");
                }
            }, function (error) { });
        }

    }]);
})();