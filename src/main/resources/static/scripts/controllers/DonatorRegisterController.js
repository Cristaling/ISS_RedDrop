(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorRegisterController', ['$location','$http','$cookies', '$mdToast', function($location,$http,$cookies, $mdToast)
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
            }else{
                $mdToast.show(
                    $mdToast.simple()
                        .textContent('Passwords do not match.')
                        .position('bottom right')
                        .theme('reddrop-toast')
                        .hideDelay(2500)
                ).then(function (value) {
                }, function (reason) {
                });
            }
        }
    }]);
})();