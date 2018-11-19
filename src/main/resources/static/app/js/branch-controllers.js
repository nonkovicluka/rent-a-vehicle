rentAVehicleApp.controller("branchSearchCtrl", function ($scope, $http, $location) {

    var baseUrlBranch = "/api/branches/all";
    var baseUrlAgency = "/api/agencies/all";

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.branches = [];
    $scope.agencies = [];

    var getBranches = function () {

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;


        $http.get(baseUrlBranch, config)
            .then(function success(data) {
                $scope.branches = data.data;
                $scope.totalPages = data.headers('totalPages');

            })

    };

    var getAgencies = function () {

        $http.get(baseUrlAgency)
            .then(function success(data) {
                $scope.agencies = data.data;
            });

    };

    getAgencies();
    getBranches();


    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getBranches();

    };


    $scope.register = function () {
        $location.path("/branches/register");

    };


});

rentAVehicleApp.controller("branchByAgencyCtrl", function ($scope, $http, $routeParams, $location, AuthService) {

    $scope.user = AuthService.user;

    $scope.branchesByAgency = [];

    $scope.agency = {};

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    var branchesUrl = "/api/branches/" + $routeParams.agencyId + "b";

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


rentAVehicleApp.controller("registerBranchCtrl", function ($scope, $http, $location, AuthService) {

    $scope.user = AuthService.user;

    var redirect = function () {
        if ($scope.user == null) {
            $location.path("/login");
        }
    };

    redirect();

    var baseUrlBranch = "/api/branches/add";
    var baseUrlAgency = "/api/agencies/" + $scope.user.id + "u";

    $scope.branches = [];
    $scope.agencies = [];

    $scope.newBranch = {};
    $scope.newBranch.address = "";
    $scope.newBranch.phoneNumber = "";
    $scope.newBranch.agencyId = "";

    var getAgencies = function () {

        $http.get(baseUrlAgency)
            .then(function success(data) {
                $scope.agencies = data.data;
            });

    };

    getAgencies();

    $scope.registerBranch = function () {
        $http.post(baseUrlBranch, $scope.newBranch)
            .then(
                function success(data) {
                    alert("Registration was successful.")
                    $location.path("/branches");
                },
                function error(data) {
                    alert("Registration failed. Try again.");
                }
            );
    };
});