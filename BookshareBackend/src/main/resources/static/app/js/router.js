'use strict';
angular.module('BookShare')
    .config(function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl : 'login.html',
            controller : 'LoginCtrl as loginCtrl'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'LoginCtrl as loginCtrl'
        }).otherwise('/');
    });