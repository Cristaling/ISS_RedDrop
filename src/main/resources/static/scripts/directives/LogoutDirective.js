(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.directive('logoutBtn', function ($cookies,$location) {
        return {
            scope: {},
            templateUrl: "views/directives/LogoutDirective.html",
            controller: function ($scope) {
                $scope.logout = function () {
                    $cookies.remove("donatorToken");
                    $cookies.remove("doctorToken");
                    $cookies.remove("adminToken");
                    $location.path("/landing");
                };
            }
        };
    });
})();