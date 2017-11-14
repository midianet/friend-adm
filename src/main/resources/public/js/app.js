angular.module('App', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/main', {
                controller: 'MainController',
                templateUrl: 'main.html'
            })
          /*  .when('/balance', {
                controller: 'BalanceController',
                templateUrl: 'saldo.html'
            })
            .when('/enrolled',{
                controller: 'EnrolledController',
                templateUrl: 'inscritos.html'
            })
            .when('/drink',{
                controller: 'EnrolledController',
                templateUrl: 'bebidas.html'
            })*/
            .otherwise({redirectTo: '/main'});
    }])
    .controller('MainController', ['$scope','$http',
        function ($scope,$http) {

        }]);
/*    .controller('BalanceController', ['$scope','$http',
        function ($scope,$http) {
            $http.get('resource/balance').success(function(data) {
                $scope.balances = data;
            });
        }])

    .controller('EnrolledController', ['$scope','$http',

        function  ($scope,$http) {
            $http.get('resource/partner/notenrolled').success(function(data) {
                $scope.noenrolleds = data;
            });
        }
    ]);*/
