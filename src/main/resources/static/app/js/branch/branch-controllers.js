rentAVehicleApp.controller("branchByAgencyCtrl", function ($scope, $http, $routeParams, $location, AuthService) {

    $scope.user = AuthService.user;

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


rentAVehicleApp.controller("registerBranchCtrl", function ($scope, $http, $location, AuthService, NgMap, branchImagesService) {

    $scope.user = AuthService.user;

    var redirect = function () {
        if (!$scope.user) {
            $location.path("/login");
        }
    };

    redirect();

    var baseUrlBranch = "/api/branches/addAll";
    var baseUrlAgency = "/api/agencies/" + $scope.user.id + "u";

    $scope.agencies = [];


    $scope.newBranch = {};
    $scope.newBranch.address = "";
    $scope.newBranch.phoneNumber = "";
    $scope.newBranch.agencyId = "";
    $scope.newBranch.latitude = "";
    $scope.newBranch.longitude = "";

    $scope.getCords = function () {
        NgMap.getGeoLocation($scope.newBranch.address).then(function (latlng) {
            $scope.newBranch.latitude = latlng.lat();
            $scope.newBranch.longitude = latlng.lng();

        });
    };

    var getAgencies = function () {

        $http.get(baseUrlAgency)
            .then(function success(data) {
                $scope.agencies = data.data;
            });

    };

    getAgencies();

    $scope.message = null;

    $scope.picFiles = [];

    $scope.deletePic = function (p) {

        $scope.picFiles.splice($scope.picFiles.indexOf(p), 1);

    };

    $scope.uploadAndSave = function () {
        branchImagesService.post(baseUrlBranch, $scope.picFiles, $scope.newBranch);
    };

});