rentAVehicleApp.controller("vehicleSearchCtrl", function ($scope, $http, $location, $routeParams, AuthService, priceListService) {

    $scope.user = AuthService.user;

    $scope.vehicles = [];

    $scope.agency = {};

    var filteredVehicleUrl = "api/vehicles/" + $routeParams.agencyId + "/all";


    $scope.filteredVehicle = {};
    $scope.filteredVehicle.name = '';
    $scope.filteredVehicle.vehicleTypeId = '';

    var getVehicles = function () {

        var config = {params: {}};


        if ($scope.filteredVehicle.name != "") {
            config.params.name = $scope.filteredVehicle.name;
        }

        if ($scope.filteredVehicle.vehicleTypeId != "") {
            config.params.vehicleTypeId = $scope.filteredVehicle.vehicleTypeId;

        }

        config.params.agencyId = $routeParams.agencyId;


        $http.get(filteredVehicleUrl, config)
            .then(function success(data) {
                $scope.vehicles = data.data;
            })

    };

    var baseUrlVehicleType = "/api/vehicleTypes/all";

    $scope.vehicleTypes = [];

    var getVehicleTypes = function () {

        $http.get(baseUrlVehicleType)
            .then(function success(data) {
                $scope.vehicleTypes = data.data;
            });

    };

    getVehicleTypes();

    getVehicles();

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

    $scope.search = function () {
        getVehicles();
    };

    $scope.addVehicle = function () {
        $location.path("agencies/" + $routeParams.agencyId + "/vehicles/add");

    };

    $scope.priceList = {};
    var currentPriceListUrl = "api/pricelists/" + $routeParams.agencyId + "pl";

    var getPriceList = function () {

        $http.get(currentPriceListUrl)
            .then(function success(data) {
                    $scope.priceList = data.data;
                },
                function error(data) {
                    alert("Current price list failed");

                }
            );

    };

    getPriceList();


    $scope.$watch("priceList", function () {

            priceListService.priceList = $scope.priceList;
        }
    );

});

rentAVehicleApp.controller("addVehicleCtrl", function ($scope, $http, $location, $routeParams, AuthService) {

    $scope.user = AuthService.user;

    var redirect = function () {
        if ($scope.user == null) {
            $location.path("/login");
        }
    };

    redirect();

    var baseUrlVehicle = "/api/vehicles/add";
    var baseUrlVehicleType = "/api/vehicleTypes/all";

    $scope.vehicleTypes = [];

    $scope.newVehicle = {};
    $scope.newVehicle.name = "";
    $scope.newVehicle.description = "";
    $scope.newVehicle.sepcification = "";
    $scope.newVehicle.vehicleTypeId = "";
    $scope.newVehicle.available = true;
    $scope.newVehicle.agencyId = $routeParams.agencyId;

    var getVehicleTypes = function () {

        $http.get(baseUrlVehicleType)
            .then(function success(data) {
                $scope.vehicleTypes = data.data;
            });

    };

    getVehicleTypes();

    $scope.addVehicle = function () {
        $http.post(baseUrlVehicle, $scope.newVehicle)
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

rentAVehicleApp.controller("reserveVehicleCtrl", function ($scope, $http, $location, $routeParams, AuthService, priceListService) {

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

    $scope.priceList = priceListService.priceList;


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

    // get priceListItem

    $scope.priceListItem = {};

    var currentPriceListItemUrl = "api/pricelistitems/" + $scope.priceList.id + "pli/" + $routeParams.vehicleId + "v";

    var getPriceListItem = function () {

        $http.get(currentPriceListItemUrl)
            .then(function success(data) {
                    $scope.priceListItem = data.data;
                }
                , function error(data) {
                    alert("Current price list item failed.");

                }
            );

    };

    getPriceListItem();


    $scope.startDateMs = null;
    $scope.endDateMs = null;
    $scope.dif = 0;

    $scope.startDate = null;
    $scope.endDate = null;

    $scope.calculateTotalPrice = function () {

        if ($scope.newReservation.startDate != null && $scope.newReservation.endDate != null) {
            $scope.startDateMs = $scope.startDate;
            $scope.endDateMs = $scope.endDate;
            $scope.dif = ($scope.endDateMs.getTime() - $scope.startDateMs.getTime()) / 3.6e+6;
            $scope.newReservation.totalPrice = Math.round($scope.dif) * $scope.priceListItem.pricePerHour;
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