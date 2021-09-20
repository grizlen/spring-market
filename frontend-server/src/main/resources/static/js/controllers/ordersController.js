marketApp.controller('ordersController',
    function ordersController($scope, $http) {
        const contextPath = 'http://localhost:5555/api/v1';
        $scope.ordersPage = null;

        $scope.getItems = function () {
            $http({
                    method: 'GET',
                    url: contextPath + '/orders'
                })
                .then(function (response) {
                    $scope.ordersPage = response.data;
                });
        };
    
//        $scope.order = function(id) {
//            $http({
//                method: 'POST',
//                url: contextPath + '/orders/' + id
//            })
//                .then(function (responcse) {
//                    $scope.getItems();
//                });
//        };
    
        $scope.getItems();
    }
)
