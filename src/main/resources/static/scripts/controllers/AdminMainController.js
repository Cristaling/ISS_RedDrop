(function(){
    'use strict'
    var app = angular.module('RedDrop');
    app.controller('AdminMainController', ['$location', '$cookies', function($location, $cookies)
    {
        var vm = this;

        vm.adminToken = $cookies.get("adminToken");

        if (!vm.adminToken) {
            $location.path("/admin/login");
        }

        vm.logout = function() {
            $cookies.remove("adminToken");
            $location.path("/admin/login");
        }

    }]);
})();