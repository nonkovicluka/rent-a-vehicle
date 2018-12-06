rentAVehicleApp.controller("addPriceList", function ($scope, $http, $location, $routeParams, AuthService) {

    $scope.user = AuthService.user;
    $scope.agency = {};

    var getAgency = function () {

        $http.get("api/agencies/" + $routeParams.agencyId)
            .then(
                function success(data) {
                    $scope.agency = data.data;
                    redirect();
                },
                function error(data) {
                }
            );

    };

    getAgency();

    var redirect = function () {

        if (!$scope.user) {
            $location.path("/login");
        }

        if ($scope.agency.ownerId !== $scope.user.id) {
            $location.path("/page-not-found");
        }
    };

    var baseUrlPriceLIstAdd = "api/pricelists/add";

    $scope.startDate = null;
    $scope.endDate = null;

    $scope.newPriceList = {};
    $scope.newPriceList.startDate = "";
    $scope.newPriceList.endDate = "";
    $scope.newPriceList.agencyId = $routeParams.agencyId;

    $scope.message = null;

    $scope.addPriceList = function () {
        $http.post(baseUrlPriceLIstAdd, $scope.newPriceList)
            .then(
                function success(data) {
                    $location.path("/my-agencies/" + $routeParams.agencyId + "/manage");
                },
                function error(data) {
                    $scope.message = "Dates are not valid.";
                }
            );
    };

});