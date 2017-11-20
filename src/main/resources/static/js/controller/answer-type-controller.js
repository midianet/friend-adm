app.controller('AnswerTypeController', ['$scope','$http','$location','$routeParams',
               function ($scope,$http,$location,$routeParams) {
        $scope.type = {};

        $scope.clear = function(){
            $scope.type = {};
            $scope.frm.$setPristine(true);
        };

        $scope.save = function (){
            var method =  $scope.type.id ? 'PUT' : 'POST';
            var url    =  $scope.type.id ? '/api/answers/types/'  + $scope.type.id : '/api/answers/types/';
            var msg    =  $scope.type.id ? 'Alterado com sucesso' : 'Criado com sucesso';
            $http({method: method, url: url  ,data:$scope.type})
                .then(function (){
                    $location.path('/resposta-tipo');
                    $.notify({message : msg},
                        {type : 'success',
                         offset: {x: 10, y: 59}});
                    } , function (response){
                    console.log(response.data);
                    console.log(response.status);
                    $.notify({message : response.data},
                        {type : 'danger',
                            offset: {x: 10, y: 59}});
                });
        };

        if($routeParams.id){
            $http({method: 'GET', url:'/api/answers/types/' + $routeParams.id})
                .then(function (response){
                    $scope.type = response.data;
                } , function (response){
                    console.log(response.data);
                    console.log(response.status);
                    $location.path('/resposta-tipo');
                    $.notify({message : response.data},
                        {type : 'danger',
                            offset: {x: 10, y: 59}});
                });
        }

    }
]);