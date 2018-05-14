(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminMainController', ['$location', '$cookies', '$http', '$mdDialog', 'apiIP', function ($location, $cookies, $http, $mdDialog, apiIP) {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.hospitals = [];

        vm.openRegisterDoctorDialog = function (hospitalToken) {
            $mdDialog.show({
                template: '<div register-doctor data-hospital-id="' + hospitalToken + '"></div>',
                clickOutsideToClose:true
            });
        };

        vm.openAddHospitalDialog = function () {
            $mdDialog.show({
                template: '<div add-hospital></div>',
                clickOutsideToClose:true
            });
        };

        // vm.addHospital = function () {
        //     var addHospitalPanel = $mdDialog.prompt()
        //         .title('Add a new hospital')
        //         .initialValue('County Hospital')
        //         .ok('Save')
        //         .cancel('Cancel');
        //
        //     $mdDialog.show(addHospitalPanel).then(function (result) {
        //         $http({
        //             method: 'POST',
        //             url: apiIP + '/api/hospital/add',
        //             data: {
        //                 uuid: "",
        //                 name: result
        //             }
        //         }).then(function (response) {
        //             vm.refreshHospitalList();
        //         }, function (error) { });
        //     }, function () {
        //     });
        // };

        vm.deleteHospital = function (hospitalId) {
            $http.get(apiIP + '/api/hospital/delete?uuid=' + hospitalId).then(function (response) {
                vm.refreshHospitalList();
            }, function (reason) {

            });
        };

        vm.refreshHospitalList = function () {
            $http.get(apiIP + '/api/hospital/getall').then(function (response) {
                vm.hospitals = response.data;
            }, function (reason) {

            });
        };

        vm.refreshHospitalList();

    }]);
})();