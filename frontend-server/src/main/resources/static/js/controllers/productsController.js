marketApp.controller('productsController',
    function ProductsController($scope, $http) {
        const contextPath = 'http://localhost:5555/api/v1';
        $scope.productPage = null;

        $scope.getProducts = function () {
            $http({
                    method: 'GET',
                    url: contextPath + '/products'
                })
                .then(function (response) {
                    $scope.productPage = response.data;
                });
        };
        $scope.addToCart = function (id) {
            $http({
                method: 'POST',
                url: contextPath + '/cart',
                data: {
                    id: id,
                    count: 1
                }
            });
        }
        $scope.getProducts();
    }
)
