(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorLoginController', ['$location', '$cookies', '$http', '$mdToast', function($location, $cookies, $http, $mdToast)
    {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (vm.doctorToken) {
            $location.path("/doctor/main");
        }

        vm.tryLoginDoctor = function () {
            $http({
                method: 'POST',
                url: '/api/doctor/login',
                data: {
                    cnp: vm.doctorCNP,
                    password: vm.doctorPassword
                }
            }).then(function (response) {
                if (response.data.succesful) {
                    $cookies.put("doctorToken", response.data.token);
                    $location.path("/doctor/main");
                }else{
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Check if the password/CNP match or if you do not have an account, please ask an administrator to create one for you.')
                            .position('bottom right')
                            .theme('reddrop-toast')
                            .hideDelay(3500)
                    ).then(function (value) {
                    }, function (reason) {
                    });
                }
            }, function (error) { });
        }

    }]);
})();