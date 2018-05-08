(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('DonatorMainController', ['$location', '$cookies', function($location, $cookies)
    {
        var vm = this;

        vm.userToken = $cookies.get("userToken");

        if (!vm.userToken) {
            $location.path("/landing");
        }

        vm.logout = function() {
            $cookies.remove("userToken");
            $location.path("/landing");
        }

    }]);
})();