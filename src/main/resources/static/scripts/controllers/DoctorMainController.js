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

        vm.openAddRequestDialog = function () {
            $mdDialog.show({
                controller : 'BloodRequestAddController',
                controllerAs: 'ctrl',
                templateUrl: '/views/dialogs/BloodRequestAddDialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose:true
            }).then(vm.refreshRequestList,vm.refreshRequestList);
        };

        vm.deleteRequest = function () {
            $http.get(apiIP + '/api/bloodrequest/delete?token=' + vm.doctorToken + '&uuid=' + vm.doctorToken).then(function (response) {
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