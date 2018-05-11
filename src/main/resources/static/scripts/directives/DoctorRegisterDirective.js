(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.directive('registerDoctor', function () {
        return {
            scope: {
                hospitalID:'@'
            },
            templateUrl: "views/directives/DoctorRegistrationDirective.html",
            controller: function () {

                var vm = this;

                vm.tryRegisterDoctor = function () {
                    http({
                        method: 'POST',
                        url: apiIP + '/api/doctor/add',
                        data: {
                            uuid: "",
                            password: vm.doctorPassword,
                            name : vm.nameRegister,
                            hospital: hospitalID,
                            cnp : vm.doctorCNP
                        }
                    }).then(function (response) {
                        if (response.data.succesful) {
                            this.close();
                        }
                    }, function (error) {
                    });
                }
            },
            controllerAs: "ctrl"
        };
    });
})();