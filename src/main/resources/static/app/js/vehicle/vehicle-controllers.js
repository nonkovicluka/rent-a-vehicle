rentAVehicleApp.controller("vehicleSearchCtrl", function ($scope, $http, $location, $routeParams, AuthService) {

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