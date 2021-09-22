marketApp.controller('productsController',
    function ProductsController($scope, $http) {
        const contextPath = 'http://localhost:5555/api/v1';
        $scope.productPage = null;

        $scope.getProducts = function (pg = 0) {
            $http({
                    method: 'GET',
                    url: contextPath + '/products',
                    params: {
                        page: pg
                    }
                })
                .then(function (response) {
                    $scope.productPage = response.data;
                    $scope.navPages = $scope.genNavPages(pg);
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
        };
    
        $scope.genNavPages = function(pg) {
            result = [];
            start = (pg - 2) < 0 ? 0 : pg - 2;
            last = $scope.productPage.totalPages - 1;
            end = (pg + 2) > last ? last : pg + 2;
            for (i = start; i <= end; result.push(i++));
            return result;
            
        };
        
    $scope.getProducts();
    }
)
