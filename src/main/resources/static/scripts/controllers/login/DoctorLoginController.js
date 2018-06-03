(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorLoginController', ['$location', '$cookies', '$http', function($location, $cookies, $http)
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
                }
            }, function (error) { });
        }

    }]);
})();