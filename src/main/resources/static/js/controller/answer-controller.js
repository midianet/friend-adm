app.controller('AnswerController', ['$scope','$http',
    function ($scope,$http) {

        $scope.list = [];
        $scope.answer = {};

        listAll = function(){

            $http({method:'GET', url:'/api/answers'})
                .then(function (response){
                    $scope.list=response.data;
                } , function (response){ //TODO Implementar mensagem de erro
                    console.log(response.data);
                    console.log(response.status);
                });
        };
        listAll();
    }
]);