rentAVehicleApp.controller("agencySearchCtrl", function ($scope, $http, $location, AuthService) {

    $scope.user = AuthService.user;

    var baseUrlAgency = "/api/agencies/all";

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.agencies = [];


    var getAgencies = function () {

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;


        $http.get(baseUrlAgency, config)
            .then(function success(data) {
                $scope.agencies = data.data;
                $scope.totalPages = data.headers('totalPages');

            })

    };

    getAgencies();


    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getAgencies();

    };

    $scope.register = function () {
        $location.path("/agencies/register");

    };


});

rentAVehicleApp.controller("registerAgencyCtrl", function ($scope, $http, $location, AuthService) {

    var baseUrlAgency = "/api/agencies/add";

    $scope.user = AuthService.user;

    var redirect = function () {
        if ($scope.user == null) {
            $location.path("/login");
        }
    };

    redirect();

    $scope.newAgency = {};
    $scope.newAgency.name = "";
    $scope.newAgency.description = "";
    $scope.newAgency.phoneNumber = "";
    $scope.newAgency.email = "";
    $scope.newAgency.ownerId = $scope.user.id;


    $scope.registerAgency = function () {
        $http.post(baseUrlAgency, $scope.newAgency)
            .then(
                function success(data) {
                    alert("Registration was successful.");
                    $location.path("/");
                },
                function error(data) {
                    alert("Registration failed. Try again.");
                }
            );
    };
});