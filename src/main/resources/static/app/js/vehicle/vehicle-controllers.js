rentAVehicleApp.controller("vehicleSearchCtrl", function ($scope, $http, $location, $routeParams, AuthService, pricePerHourService) {

    $scope.user = AuthService.user;

    var priceListItemVehiclesUrl = "api/pricelistitems/vehicles";

    $scope.vehiclesAndPrice = [];

    $scope.filteredVehicle = {};
    $scope.filteredVehicle.name = '';
    $scope.filteredVehicle.vehicleTypeId = '';

    $scope.getVehiclesAndPrice = function () {

        var config = {params: {}};

        if ($scope.filteredVehicle.name != "") {
            config.params.name = $scope.filteredVehicle.name;
        }

        if ($scope.filteredVehicle.vehicleTypeId != "") {
            config.params.vehicleTypeId = $scope.filteredVehicle.vehicleTypeId;

        }

        config.params.agencyId = $routeParams.agencyId;

        $http.get(priceListItemVehiclesUrl, config)
            .then(function success(data) {
                    $scope.vehiclesAndPrice = data.data;
                },
                function error(data) {
                    alert("Vehicles and price list item failed to get.");

                });

    };

    $scope.getVehiclesAndPrice();


    var baseUrlVehicleType = "/api/vehicleTypes/all";

    $scope.vehicleTypes = [];

    var getVehicleTypes = function () {

        $http.get(baseUrlVehicleType)
            .then(function success(data) {
                $scope.vehicleTypes = data.data;
            });

    };

    getVehicleTypes();


    $scope.agency = {};

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

    $scope.addVehicle = function () {
        $location.path("agencies/" + $routeParams.agencyId + "/vehicles/add");

    };


    $scope.getPricePerHour = function (pricePerHour) {

        pricePerHourService.pricePerHour = pricePerHour;

    };


    $scope.reserve = function (vehicleId) {

        $location.path("/agencies/" + $routeParams.agencyId + "/vehicles/" + vehicleId + "/reserve");
    };

});

rentAVehicleApp.controller("reserveVehicleCtrl", function ($scope, $http, $location, $routeParams, AuthService, pricePerHourService) {

    // redirect

    $scope.user = AuthService.user;

    var redirect = function () {
        if ($scope.user == null) {
            $location.path("/login");
        }
    };

    redirect();


    var branchesUrl = "/api/branches/" + $routeParams.agencyId + "b";
    var reservationsUrl = "/api/reservations/add";

    $scope.pricePerHour = pricePerHourService.pricePerHour;


    // new reservation

    $scope.newReservation = {};
    $scope.newReservation.startDate = "";
    $scope.newReservation.endDate = "";
    $scope.newReservation.totalPrice = 0.00;
    $scope.newReservation.userId = $scope.user.id;
    $scope.newReservation.vehicleId = $routeParams.vehicleId;
    $scope.newReservation.branchPickupId = "";
    $scope.newReservation.branchDeliveryId = "";


    // get branches by agency

    $scope.branchesByAgency = [];

    var getBranches = function () {

        $http.get(branchesUrl)
            .then(function success(data) {
                $scope.branchesByAgency = data.data;
            });
    };

    getBranches();



    $scope.dif = 0;

    $scope.startDate = null;
    $scope.endDate = null;

    $scope.calculateTotalPrice = function () {

        if ($scope.newReservation.startDate != null && $scope.newReservation.endDate != null) {

            $scope.dif = ($scope.endDate.getTime() - $scope.startDate.getTime()) / 3.6e+6;
            $scope.newReservation.totalPrice = Math.ceil($scope.dif) * $scope.pricePerHour;
            $scope.newReservation.startDate = $scope.startDate.toISOString();
            $scope.newReservation.endDate = $scope.endDate.toISOString();
        }

    };


    $scope.reserveVehicle = function () {
        $http.post(reservationsUrl, $scope.newReservation)
            .then(
                function success(data) {
                    alert("Reservation was successful.");
                    $location.path("/");
                },
                function error(data) {
                    alert("Reservation failed. Try again.");
                }
            );
    };

})
;