rentAVehicleApp.controller('NavController', function ($http, $scope, AuthService, $location, $rootScope) {
    $scope.$on('LoginSuccessful', function () {
        $scope.user = AuthService.user;
    });
    $scope.$on('LogoutSuccessful', function () {
        $scope.user = null;
    });

    $scope.$on('$locationChangeSuccess', function() {
        var path = $location.path();

        $scope.templateUrl =
            (path !== '/agencies' && path !== "/my-agencies" && path.indexOf("reserve") === -1
                && path.indexOf("branches") === -1 && path.indexOf("rate") === -1
                && path !== "/agencies/register" &&  path !== "/branches/register"
                && path.indexOf("manage") === -1 && path.indexOf("vehicles/add") === -1
                && path.indexOf("reservations") === -1)
                ? 'app/html/auth/nav-transparent-directive.html' : 'app/html/auth/nav-orange-directive.html' ;
    });

    $scope.logout = function () {
        AuthService.user = null;
        $rootScope.$broadcast('LogoutSuccessful');
        $location.path('login');
    };
    $scope.myAgencies = function () {
        $location.path("/my-agencies")

    }
});
