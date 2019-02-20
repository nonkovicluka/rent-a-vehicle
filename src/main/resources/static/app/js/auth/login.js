rentAVehicleApp.controller('LoginController', function ($http, $scope, $location, AuthService, $rootScope, SocketService) {

    // redirect

    $scope.user = AuthService.getUser();

    var redirect = function () {
        if ($scope.user) {
            $location.path("/");
        }
    };

    redirect();

    // method for login
    $scope.login = function () {
        // requesting the token by usename and passoword
        $http({
            url: 'authenticate',
            method: "POST",
            params: {
                username: $scope.username,
                password: $scope.password
            }
        }).success(function (res) {
            $scope.password = null;
            // checking if the token is available in the response
            if (res.token) {
                // var stompClient = SocketService.getSocketInstance();
                // stompClient.send("/app/register", {}, JSON.stringify(res.user));
                localStorage.token = res.token;
                localStorage.user = JSON.stringify(res.user);
                $scope.message = '';
                // setting the Authorization Bearer token with JWT token
                $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;
                // setting the user in AuthService
                $rootScope.$broadcast('LoginSuccessful');
                // going to the home page
                $location.path("/");

                if (res.user.userRoleName === "ROLE_ADMIN") {
                    SocketService.connect();
                }
            } else {
                // if the token is not present in the response then the
                // authentication was not successful. Setting the error message.
                $scope.message = 'Authetication Failed !';
            }
        }).error(function (error) {
            // if authentication was not successful. Setting the error message.
            $scope.message = 'Authetication Failed !';
        });
    };

});
