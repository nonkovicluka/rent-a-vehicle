rentAVehicleApp.controller('HomeController', function ($http, $scope, AuthService) {
    $scope.user = AuthService.user;
});
