var marketApp = angular.module('marketApp', ['ngRoute', 'ngStorage'])
    .config(function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'views/login.html',
            controller: 'loginController'
        });
        $routeProvider.when('/products', {
            templateUrl: 'views/products.html',
            controller: 'productsController'
        });
        $routeProvider.when('/cart', {
            templateUrl: 'views/cart.html',
            controller: 'cartController'
        });
        $routeProvider.when('/orders', {
            templateUrl: 'views/orders.html',
            controller: 'ordersController'
        });
        $routeProvider.otherwise({
            redirectTo: '/products'
        });
    }).run(function ($rootScope, $templateCache, $http, $localStorage) {
        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            if (typeof (current) !== 'undefined') {
                $templateCache.remove(current.templateUrl);
            }
        });
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
            console.log($localStorage.currentUser);
        }
    });
