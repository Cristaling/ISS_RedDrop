(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorLoginController', ['$location', '$cookies', '$http','$mdToast', function($location, $cookies, $http,$mdToast)
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
                url: '/api/donator/login',
                data: {
                    cnp: vm.donatorCNP,
                    password: vm.donatorPassword
                }
            }).then(function (response) {
                if (response.data.succesful) {
                    $cookies.put("donatorToken", response.data.token);
                    $location.path("/donator/main");
                }else{
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Check if the password/cnp match, id they do please verify your email to confirm the account.')
                            .position('bottom right')
                            .theme('reddrop-toast')
                            .hideDelay(2500)
                    ).then(function (value) {
                    }, function (reason) {
                    });
                }
            }, function (error) { });
        }

    }]);
})();