rentAVehicleApp.controller("branchByAgencyCtrl", function ($scope, $http, $routeParams, $location, AuthService) {

    $scope.user = AuthService.getUser();

    $scope.branchesByAgency = [];

    $scope.agency = {};

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    var branchesUrl = "/api/branches/" + $routeParams.agencyId + "bPages";

    var getBranches = function () {

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        $http.get(branchesUrl, config)
            .then(function success(data) {
                    $scope.branchesByAgency = data.data;
                    $scope.totalPages = data.headers('totalPages');

                },
                function error(data) {
                }
            );
    };


    var getAgency = function () {

        $http.get("api/agencies/" + $routeParams.agencyId)
            .then(
                function success(data) {
                    $scope.agency = data.data;
                },
                function error(data) {
                }
            );

    };

    $scope.imagesByAgency = [];


    var baseUrlBranchImages = "/api/branchImages/allByAgency";

    var getBranchImages = function () {

        var config = {params: {}};

        config.params.agencyId = $routeParams.agencyId;

        $http.get(baseUrlBranchImages, config)
            .then(function success(data) {
                    $scope.imagesByAgency = data.data;
                }
                , function error(data) {
                    alert("images failed to get")

                }
            )
    };

    getBranchImages();

    getAgency();

    getBranches();


    $scope.agenciesByUser = [];


    var getAgenciesByUser = function () {

        if ($scope.user != null) {

            var agenciesByUserUrl = "/api/agencies/" + $scope.user.id + "u";

            $http.get(agenciesByUserUrl)
                .then(function success(data) {
                    $scope.agenciesByUser = data.data;
                });

        }


    };

    getAgenciesByUser();

    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getBranches();

    };

    $scope.register = function () {
        $location.path("/branches/register");

    };

});