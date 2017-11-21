app.controller('QuestionController', ['$scope','$http','$location','$routeParams',
    function ($scope,$http,$location,$routeParams) {
        $scope.question = {};

        $scope.save = function (){
            var method =  $scope.question.id ? 'PUT' : 'POST';
            var url    =  $scope.question.id ? '/api/questions/'  + $scope.question.id : '/api/questions/';
            var msg    =  $scope.question.id ? 'Alterado com sucesso' : 'Criado com sucesso';
            $http({method: method, url: url  ,data:$scope.question})
                .then(function (){
                    $location.path('/pergunta');
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

        // if($routeParams.id){
        //     $http({method: 'GET', url: '/api/questions/types/'})
        //         .then(function (response){
        //             $scope.types =  response.data;
        //             $("#type-search").each(function() {
        //                 $(this).select2({allowClear: true,
        //                                  placeholder: "Tipo"},
        //                                 {data: $(this).data()});
        //             });
        //             $http({method: 'GET', url:'/api/answers/' + $routeParams.id})
        //                 .then(function (response){
        //                     $scope.question = response.data;
        //                     setTimeout(function(){
        //                         $scope.propagate = false;
        //                         $("#type-search").val($scope.question.type.id).trigger("change");
        //                         $scope.propagate = true;
        //                     },0);
        //                 } , function (response){
        //                     console.log(response.data);
        //                     console.log(response.status);
        //                     $location.path('/resposta');
        //                     $.notify({message : response.data},
        //                         {type : 'danger',
        //                             offset: {x: 10, y: 59}});
        //                 });
        //
        //         } , function (response){
        //             console.log(response.data);
        //             console.log(response.status);
        //             $location.path('/resposta');
        //             $.notify({message : response.data},
        //                 {type : 'danger',
        //                     offset: {x: 10, y: 59}});
        //         });
        // }else{
        //     $http({method: 'GET', url: '/api/answers/types/'})
        //         .then(function (response){
        //             $scope.types =  response.data;
        //             $("#type-search").each(function() {
        //                 $(this).select2({allowClear: true,
        //                         placeholder: "Tipo"},
        //                     {data: $(this).data()});
        //             });
        //         } , function (response){
        //             console.log(response.data);
        //             console.log(response.status);
        //             $.notify({message : response.data},
        //                 {type : 'danger',
        //                     offset: {x: 10, y: 59}});
        //         });
        // }
    }
]);