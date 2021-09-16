marketApp.controller('authController',
    function HomeController($scope, $http, $localStorage) {

        const contextPath = 'http://localhost:5555/api/v1';

        $scope.user = null;

        $scope.signUp = function() {
            $http({
                method: 'POST',
                url: contextPath + '/auth/signup',
                data: {
                    login: $scope.user.login,
                    password: $scope.user.password
                }
            })
            .then(function (response) {
                $scope.user.token = response.data;
                $http.defaults.headers.common.Authorization = $scope.user.token.token;
                $localStorage.currentUser = $scope.user;
            });
        };

        $scope.logIn = function() {
            $http({
                method: 'POST',
                url: contextPath + '/auth/login',
                data: {
                    login: $scope.user.login,
                    password: $scope.user.password
                }
            })
            .then(function (response) {
                $scope.user.token = response.data;
                $http.defaults.headers.common.Authorization = $scope.user.token.token;
                $localStorage.currentUser = $scope.user;
            });
        };
    
        $scope.logOut = function() {
            $http({
                method: 'GET',
                url: contextPath + '/auth/logout',
            })
            .then(function (response) {
                $scope.user = null;
                $http.defaults.headers.common.Authorization = '';
                delete $localStorage.currentUser;
            });
        };
    
    if ($localStorage.currentUser) {
        $scope.user = $localStorage.currentUser;
        $scope.logIn();
    }        
    }
)