(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorLoginController', ['$location', '$cookies', '$http', 'apiIP', function($location, $cookies, $http, apiIP)
    {
        var vm = this;

        vm.userToken = $cookies.get("userToken");

        if (vm.userToken) {
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
                    $cookies.put("userToken", response.data.token);
                    $location.path("/donator/main");
                }
            }, function (error) { });
        }

    }]);
})();