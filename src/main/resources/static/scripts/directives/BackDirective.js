(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.directive('backBtn', function () {
        return {
            scope: {},
            templateUrl: "views/directives/BackDirective.html",
            controller: function ($scope) {
                $scope.back = function () {
                    window.history.back();
                };
            }
        };
    });
})();