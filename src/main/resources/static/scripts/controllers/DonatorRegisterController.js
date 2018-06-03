(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorRegisterController', ['$location','$http','$cookies', function($location,$http,$cookies)
    {
        var vm = this;

        vm.donatorToken = $cookies.get("donatorToken");

        if (vm.donatorToken) {
            $location.path("/donator/main");
        }

        vm.goToRegisterDonator = function () {
            if(vm.donatorPassword === vm.donatorConfirmPassword){
                $http({
                    method: 'POST',
                    url: '/api/donator/register',
                    data: {
                        uuid : "",
                        nume : vm.donatorSurname,
                        prenume : vm.donatorFirstName,
                        cnp : vm.donatorCNP,
                        nrTel : vm.donatorTelephone,
                        password : vm.donatorPassword,
                        city : vm.donatorCity,
                        county : vm.donatorCounty,
                        email:vm.donatorEmail,
                        address : vm.donatorAddress
                    }
                }).then(function (response) {
                    $location.path("/donator/login");
                }, function (error) { });
            }
        }
    }]);
})();