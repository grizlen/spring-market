marketApp.controller('loginController',
    function loginController($scope, $http, $localStorage) {

        const contextPath = 'http://localhost:5555/api/v1';

        $scope.user = {};

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
            }, function (response) {
                alert(response.data.message);
                $scope.userFree();
            });
        };
    
        $scope.logOut = function() {
            $http({
                method: 'GET',
                url: contextPath + '/auth/logout',
            })
            .then(function (response) {
                $scope.userFree();
//                $scope.user = null;
//                $http.defaults.headers.common.Authorization = '';
//                delete $localStorage.currentUser;
            });
        };

        $scope.userFree = function() {
            $scope.user = {};
            $http.defaults.headers.common.Authorization = '';
            delete $localStorage.currentUser;
        };

        $scope.isUserLogin = function() {
            return $scope.user.token != null;
        };

    if ($localStorage.currentUser) {
        $scope.user = $localStorage.currentUser;
        $scope.logIn();
    }        
    }
)