rentAVehicleApp.controller('RegisterController', function ($http, $scope, $location, userDoc, AuthService) {

    $scope.user = AuthService.getUser();

    var redirect = function () {

        if ($scope.user) {
            $location.path("/");
        }
    };

    redirect();

    var baseUrlRegister = "/register";

    $scope.appUser = {};
    $scope.appUser.username = "";
    $scope.appUser.password = "";
    $scope.appUser.docImage = "";

    $scope.userRole = "";

    $scope.confirmPassword = "";

    $scope.doc = null;

    $scope.message = null;

    $scope.uploadAndSave = function () {
        userDoc.post(baseUrlRegister, $scope.doc, $scope.appUser, $scope.userRole);
    };

});
