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

rentAVehicleApp.controller("rateAgencyCtrl", function ($scope, $location, $http, $routeParams, AuthService) {

    $scope.user = AuthService.user;

    var redirect = function () {
        if (!$scope.user) {
            $location.path("/login")
        }
    };

    redirect();

    $scope.newRating = {};
    $scope.newRating.score = "";
    $scope.newRating.comment = "";
    $scope.newRating.agencyId = $routeParams.agencyId;
    $scope.message = null;

    var baseUrlRating = "api/ratings/add";

    $scope.addRating = function () {

        var config = {params: {}};

        config.params.userId = $scope.user.id;
        config.params.agencyId = $routeParams.agencyId;

        $http.post(baseUrlRating, $scope.newRating, config)
            .then(
                function success(data) {
                    $location.path("/agencies/" + $routeParams.agencyId + "/vehicles");
                },
                function error(data) {
                    $scope.message = "Invalid rate."
                }
            );
    };

});