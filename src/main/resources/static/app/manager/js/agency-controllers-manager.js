rentAVehicleApp.controller("myAgenciesCtrl", function ($scope, $http, $location, AuthService) {

    $scope.user = AuthService.user;

    var redirect = function () {

        if (!$scope.user) {
            $location.path("/login");
        }
    };

    redirect();


    var baseUrlAgenciesByUser = "/api/agencies/" + $scope.user.id + "uPages";

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.agencies = [];


    var getAgenciesByUser = function () {

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;


        $http.get(baseUrlAgenciesByUser, config)
            .then(function success(data) {
                $scope.agencies = data.data;
                $scope.totalPages = data.headers('totalPages');

            })

    };

    getAgenciesByUser();


    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getAgenciesByUser();

    };

    $scope.registerAgency = function () {
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

    $scope.message = null;

    $scope.registerAgency = function () {
        $http.post(baseUrlAgency, $scope.newAgency)
            .then(
                function success(data) {
                    $location.path("/");
                },
                function error(data) {
                    $scope.message = "Agency registration failed.";
                }
            );
    };
});