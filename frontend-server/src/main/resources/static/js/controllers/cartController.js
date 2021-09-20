marketApp.controller('cartController',
    function cartController($scope, $http) {
        const contextPath = 'http://localhost:5555/api/v1';
        $scope.cartPage = null;

        $scope.getItems = function () {
            $http({
                    method: 'GET',
                    url: contextPath + '/cart'
                })
                .then(function (response) {
                    $scope.cartPage = response.data;
                });
        };
    
        $scope.order = function(id) {
            $http({
                method: 'POST',
                url: contextPath + '/orders/' + id
            })
                .then(function (responcse) {
                    $scope.getItems();
                });
        };
    
        $scope.getItems();
    }
)
