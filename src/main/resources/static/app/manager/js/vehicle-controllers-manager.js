rentAVehicleApp.controller("agencyVehicleCtrl", function ($scope, $http, $location, $routeParams, AuthService) {

    $scope.user = AuthService.user;


    var redirect = function () {

        if (!$scope.user) {
            $location.path("/login");
        }

        if ($scope.agency.ownerId !== $scope.user.id) {
            $location.path("/page-not-found");
        }
    };


    $scope.currentPriceList = {};



    var baseUrlCurrentPriceList = "api/pricelists/" + $routeParams.agencyId + "/pl";

    var getCurrentPriceList = function () {

        $http.get(baseUrlCurrentPriceList)
            .then(function success(data) {
                $scope.currentPriceList = data.data;
                $scope.getVehiclesAndPrice();
            });

    };

    getCurrentPriceList();

    var priceListItemVehiclesUrl = "api/pricelistitems/vehicles";

    $scope.vehiclesAndPrice = [];

    $scope.filteredVehicle = {};
    $scope.filteredVehicle.name = '';
    $scope.filteredVehicle.vehicleTypeId = '';

    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.getVehiclesAndPrice = function () {

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if ($scope.filteredVehicle.name != "") {
            config.params.name = $scope.filteredVehicle.name;
        }

        if ($scope.filteredVehicle.vehicleTypeId != "") {
            config.params.vehicleTypeId = $scope.filteredVehicle.vehicleTypeId;

        }

        config.params.priceListId = $scope.currentPriceList.id;
        config.params.agencyId = $routeParams.agencyId;

        $http.get(priceListItemVehiclesUrl, config)
            .then(function success(data) {
                    $scope.vehiclesAndPrice = data.data;
                    $scope.totalPages = data.headers('totalPages');
                },
                function error(data) {

                });

    };

    $scope.go = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        $scope.getVehiclesAndPrice();

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

    $scope.priceLists = [];

    var baseUrlPriceLists = "api/pricelists/" + $routeParams.agencyId + "pl";

    var getPriceLists = function () {

        $http.get(baseUrlPriceLists)
            .then(function success(data) {
                $scope.priceLists = data.data;
            });

    };

    getPriceLists();


    $scope.addVehicle = function () {
        $location.path("agencies/" + $routeParams.agencyId + "/vehicles/add");

    };


    $scope.addPriceList = function () {
        $location.path("my-agencies/" + $routeParams.agencyId + "/manage/add-price-list");

    };

    $scope.registerBranch = function () {
        $location.path("/branches/register");

    };


    $scope.oldVehicle = {};

    $scope.editVehicle = function (vehicleAndPrice) {

        $scope.oldVehicle = vehicleAndPrice;
    };


    $scope.saveEdit = function () {

        $http.put("api/vehicles/edit", $scope.oldVehicle)
            .then(
                function success(data) {
                    $scope.oldVehicle = {};
                    $scope.getVehiclesAndPrice();
                },
                function error(data) {


                }
            )

    };

    $scope.deleteVehicle = function (vehicleAndPrice) {
        $http.put("api/vehicles/delete", vehicleAndPrice)
            .then(
                function success(data) {
                    $scope.getVehiclesAndPrice();
                },
                function error(data) {


                }
            )

    };


    $scope.reservations = function () {

        $location.path("/my-agencies/" + $routeParams.agencyId + "/reservations")

    };

    console.log($scope)

});

rentAVehicleApp.controller("addVehicleCtrl", function ($scope, $http, $location, $routeParams, $rootScope, AuthService, vehicleImagesService) {

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


    var baseUrlVehicle = "/api/vehicles/addAll";
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


    $scope.newVehicle.pricePerHour = "";
    $scope.newVehicle.priceListId = "";


    $scope.currentPriceList = {};

    var baseUrlCurrentPriceList = "api/pricelists/" + $routeParams.agencyId + "/pl";

    var getCurrentPriceList = function () {

        $http.get(baseUrlCurrentPriceList)
            .then(function success(data) {
                $scope.currentPriceList = data.data;
                $scope.newVehicle.priceListId = $scope.currentPriceList.id;
            });

    };

    getCurrentPriceList();


    $scope.picFiles = [];

    $scope.deletePic = function (p) {

        $scope.picFiles.splice($scope.picFiles.indexOf(p), 1);

    };

    $rootScope.message = null;

    $scope.uploadAndSave = function () {
        vehicleImagesService.post(baseUrlVehicle, $scope.picFiles, $scope.newVehicle);
    };

});
