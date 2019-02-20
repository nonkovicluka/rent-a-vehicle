rentAVehicleApp.controller("myAgenciesCtrl", function ($scope, $http, $location, AuthService) {

    $scope.user = AuthService.getUser();

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

rentAVehicleApp.controller("registerAgencyCtrl", function ($scope, $http, $location, AuthService, agencyLogo) {

    var baseUrlAgency = "/api/agencies/add";

    $scope.user = AuthService.getUser();

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

    $scope.logo = null;

    $scope.message = null;

    $scope.uploadAndSave = function () {
        agencyLogo.post(baseUrlAgency, $scope.logo, $scope.newAgency);
    };
});