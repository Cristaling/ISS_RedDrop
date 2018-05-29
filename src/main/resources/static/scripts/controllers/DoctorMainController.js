(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DoctorMainController', ['$location', '$cookies','$http','$mdDialog','apiIP', function ($location, $cookies,$http,$mdDialog,apiIP) {
        var vm = this;

        vm.doctorToken = $cookies.get("doctorToken");

        if (!vm.doctorToken) {
            $location.path("/doctor/login");
        }

        vm.requests = [];
        vm.bloodTypes = [];

        $http.get(apiIP + '/api/bloodtype/getall?token=' + vm.doctorToken).then(function (response) {
            vm.bloodTypes = response.data;
        }, function (reason) { });

        vm.getBloodTypeByUUID = function (uuid) {
            for (var i in vm.bloodTypes) {
                if (vm.bloodTypes[i].uuid === uuid) {
                    return vm.bloodTypes[i].type;
                }
            }
            return "";
        };

        vm.openAddRequestDialog = function () {
            $mdDialog.show({
                controller : 'BloodRequestAddController',
                controllerAs: 'ctrl',
                templateUrl: '/views/dialogs/BloodRequestAddDialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose:true
            }).then(vm.refreshRequestList,vm.refreshRequestList);
        };

        vm.deleteRequest = function (requestID) {
            $http.get(apiIP + '/api/bloodrequest/delete?token=' + vm.doctorToken + '&uuid=' + requestID).then(function (response) {
                vm.refreshRequestList();
            }, function (reason) {

            });
        };

        vm.refreshRequestList = function () {
            $http.get(apiIP + '/api/bloodrequest/getfrom?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
                vm.requests = response.data;
            }, function (reason) {});
        };

        vm.refreshRequestList(vm.doctorToken);

    }]);
})();