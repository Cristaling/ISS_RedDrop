(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorLoginController', ['$location', '$cookies', '$http', 'apiIP', function($location, $cookies, $http, apiIP)
    {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (vm.doctorToken) {
            $location.path("/doctor/main");
        }

        vm.tryLoginDoctor = function () {
            $http({
                method: 'POST',
                url: apiIP + '/api/doctor/login',
                data: {
                    cnp: vm.doctorCNP,
                    password: vm.doctorPassword
                }
            }).then(function (response) {
                if (response.data.succesful) {
                    $cookies.put("doctorToken", response.data.token);
                    $location.path("/doctor/main");
                }
            }, function (error) { });
        }

    }]);
})();