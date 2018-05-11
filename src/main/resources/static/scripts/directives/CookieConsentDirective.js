(function () {
    'use strict'
    var app = angular.module('RedDrop');
    app.directive('consent', function ($cookies) {
        return {
            scope: {},
            templateUrl: "views/directives/CookieDirective.html",
            controller: function ($scope) {
                var _consent = $cookies.get('consent');
                $scope.consent = function (consent) {
                    if (consent === undefined) {
                        return _consent;
                    } else if (consent) {
                        $cookies.put('consent', true);
                        _consent = true;
                    }
                };
            }
        };
    });
})();