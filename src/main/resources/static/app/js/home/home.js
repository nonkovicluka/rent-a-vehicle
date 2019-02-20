rentAVehicleApp.controller("homeCtrl", function ($scope, $http, $location, AuthService, pricePerHourService) {

    $scope.user = AuthService.getUser();

    var baseUrlAgency = "/api/agencies/all";
    var baseUrlVehicle = "api/vehicles/top3";
    var baseUrlPli = "api/pricelistitems/top3";

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.agencies = [];
    $scope.vehicles = [];
    $scope.plis = [];


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

    var getVehicles = function () {

        $http.get(baseUrlVehicle)
            .then(function success(data) {
                $scope.vehicles = data.data;
            })
    };

    getVehicles();

    $scope.vehicleImages = [];


    var baseUrlVehicleImages = "/api/vehicleImages/top3";

    var getVehicleImages = function () {

        $http.get(baseUrlVehicleImages)
            .then(function success(data) {
                    $scope.vehicleImages = data.data;
                }
                , function error(data) {

                }
            )
    };

    getVehicleImages();

    var getPlis = function () {

        $http.get(baseUrlPli)
            .then(function success(data) {
                    $scope.plis = data.data;
                }
                , function error(data) {

                }
            )
    };

    getPlis();


    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getAgencies();

    };

    $scope.getPricePerHour = function (pricePerHour) {

        pricePerHourService.pricePerHour = pricePerHour;

    };

    $scope.reserve = function (agencyId, vehicleId) {

        $location.path("/agencies/" + agencyId + "/vehicles/" + vehicleId + "/reserve");
    };

});