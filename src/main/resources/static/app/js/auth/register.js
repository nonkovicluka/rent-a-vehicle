rentAVehicleApp.controller('RegisterController', function ($http, $scope, $location) {
    $scope.submit = function () {
        $http.post('register', $scope.appUser).success(function (res) {
            $location.path("/");
            $scope.appUser = null;
            $scope.confirmPassword = null;
            $scope.register.$setPristine();
            $scope.message = "Registration successfull !";


        }).error(function (error) {
            $scope.message = error.message;
        });
    };
});
