rentAVehicleApp.controller('userApprovalCtrl', function ($http, $scope, $location, AuthService) {

    $scope.user = AuthService.getUser();

    var redirect = function () {

        if (!$scope.user) {
            $location.path("/login");
        }

        if ($scope.user.userRoleName !== "ROLE_ADMIN") {
            $location.path("/page-not-found");
        }
    };

    redirect();

    var usersBaseUrl = "/api/users/usersApproval";

    $scope.users = [];

    var getUsers = function () {

        $http.get(usersBaseUrl)
            .then(
                function success(data) {
                    $scope.users = data.data;
                },
                function error(data) {
                }
            );
    };

    getUsers();

    $scope.approveUser = function (approvedUser) {

        $http.put("api/users/approve", approvedUser)
            .then(
                function success(data) {
                    getUsers();
                },
                function error(data) {


                }
            )

    };

    $scope.banUser = function (bannedUser) {

        $http.put("api/users/ban", bannedUser)
            .then(
                function success(data) {
                    getUsers();
                },
                function error(data) {


                }
            )

    };

});