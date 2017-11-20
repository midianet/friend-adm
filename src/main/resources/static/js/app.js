var app = angular.module('App', ['ngRoute','goDataTable']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'view/main.html',
            controller: 'MainController'
        })
        .when('/resposta', {
            templateUrl: 'view/answer-list.html',
            controller: 'AnswerController'
        })
        .when('/resposta-tipo', {
            templateUrl: 'view/answer-type-list.html',
            controller: 'AnswerTypeController'
        })
        .when('/resposta-tipo-form', {
            templateUrl: 'view/answer-type-form.html',
            controller: 'AnswerTypeController'
        })
        .when('/resposta-tipo-form/:id', {
            templateUrl: 'view/answer-type-form.html',
            controller: 'AnswerTypeController'
        })
        .when('/resposta-form', {
            templateUrl: 'view/answer-form.html',
            controller: 'AnswerController'
        })
        .otherwise({
            rediretTo : '/'
        });
    $locationProvider.html5Mode(true);
});

