(function () {
    'use strict'
    var app = angular.module('RedDrop', ['ngRoute', 'ngAnimate', 'ngCookies', 'ngAria', 'ngMaterial', 'ngMessages'])

    app.config(function ($routeProvider) {
        $routeProvider
            .when("/landing", {
                templateUrl: "views/LandingPage.html",
                controller: 'LandingController',
                controllerAs: 'ctrl'
            })
            .when("/donator/login", {
                templateUrl: "views/login/DonatorLoginPage.html",
                controller: 'DonatorLoginController',
                controllerAs: 'ctrl'
            })
            .when("/donator/register", {
                templateUrl: "views/DonatorRegisterPage.html",
                controller: 'DonatorRegisterController',
                controllerAs: 'ctrl'
            })
            .when("/donator/main", {
                templateUrl: "views/DonatorMainPage.html",
                controller: 'DonatorMainController',
                controllerAs: 'ctrl'
            })
            .when("/donator/analysis/:visitToken", {
                templateUrl: "views/donator/AnalysisShowPage.html",
                controller: 'AnalysisShowController',
                controllerAs: 'ctrl'
            })
            .when("/admin/login", {
                templateUrl: "views/login/AdminLoginPage.html",
                controller: 'AdminLoginController',
                controllerAs: 'ctrl'
            })
            .when("/admin/main", {
                templateUrl: "views/admin/AdminMainPage.html",
                controller: 'AdminMainController',
                controllerAs: 'ctrl'
            })
            .when("/admin/requests", {
                templateUrl: "views/admin/RequestManagementPage.html",
                controller: 'RequestManagementController',
                controllerAs: 'ctrl'
            })
            .when("/admin/visits", {
                templateUrl: "views/admin/VisitManagementPage.html",
                controller: 'VisitManagementController',
                controllerAs: 'ctrl'
            })
            .when("/doctor/login", {
                templateUrl: "views/login/DoctorLoginPage.html",
                controller: 'DoctorLoginController',
                controllerAs: 'ctrl'
            })
            .when("/doctor/main", {
                templateUrl: "views/DoctorMainPage.html",
                controller: 'DoctorMainController',
                controllerAs: 'ctrl'
            })
            .when("/crud/doctor/:hospitalToken", {
                templateUrl: "views/crud/DoctorCRUDPage.html",
                controller: 'DoctorCRUDController',
                controllerAs: 'ctrl'
            })
            .when("/crud/hospital", {
                templateUrl: "views/crud/HospitalCRUDPage.html",
                controller: 'HospitalCRUDController',
                controllerAs: 'ctrl'
            })
            .when("/crud/donator", {
                templateUrl: "views/crud/DonatorCRUDPage.html",
                controller: 'DonatorCRUDController',
                controllerAs: 'ctrl'
            })
            .when("/admin/bloodanalysis/", {
                templateUrl: "views/admin/BloodAnalysisPage.html",
                controller: 'AdminBloodAnalysisController',
                controllerAs: 'ctrl'
            })
            .otherwise({redirectTo: "/landing"});
    });
    app.config(function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .dark();
        $mdThemingProvider.theme('reddrop-toast');
    });
})();