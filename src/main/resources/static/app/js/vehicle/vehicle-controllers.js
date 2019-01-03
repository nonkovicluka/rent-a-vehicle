rentAVehicleApp.controller("vehicleSearchCtrl", function ($scope, $http, $location, $routeParams, pricePerHourService) {

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


    $scope.imagesByAgency = [];


    var baseUrlVehicleImages = "/api/vehicleImages/allByAgency";

    var getVehicleImages = function () {

        var config = {params: {}};

        config.params.agencyId = $routeParams.agencyId;


        $http.get(baseUrlVehicleImages, config)
            .then(function success(data) {
                    $scope.imagesByAgency = data.data;
                }
                , function error(data) {
                    alert("images failed to get")

                }
            )
    };

    getVehicleImages();

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

    $scope.rateAgency = function () {

        $location.path("/agencies/" + $routeParams.agencyId + "/rate")

    };

    $scope.agencyBranches = function () {

        $location.path("/agencies/" + $routeParams.agencyId + "/branches");

    };

    $scope.avgRating = 0.0;

    var getAverageRating = function () {

        var config = {params: {}};
        config.params.agencyId = $routeParams.agencyId;

        $http.get("api/ratings/avgScore", config)
            .then(
                function success(data) {
                    $scope.avgRating = Math.round(data.data * 100) / 100;
                },
                function error(data) {
                }
            );

    };

    getAverageRating();

})
;

rentAVehicleApp.controller("reserveVehicleCtrl", function ($scope, $http, $location, $routeParams, AuthService, pricePerHourService) {

    // redirect

    $scope.user = AuthService.user;

    var redirect = function () {
        if (!$scope.user) {
            $location.path("/login");
        }
    };

    redirect();


    var branchesUrl = "/api/branches/" + $routeParams.agencyId + "b";
    var reservationsUrl = "/api/reservations/add";

    $scope.pricePerHour = pricePerHourService.pricePerHour;


    // <<< date picker code start

    $(function () {
        $('#startDate').datetimepicker({
                icons: {
                    time: "fa fa-clock-o",
                    date: "fa fa-calendar",
                    up: "fa fa-chevron-up",
                    down: "fa fa-chevron-down",
                    previous: 'fa fa-chevron-left',
                    next: 'fa fa-chevron-right',
                    today: 'fa fa-screenshot',
                    clear: 'fa fa-trash',
                    close: 'fa fa-remove'
                }
                , format: "DD-MM-YYYY  HH:mm"

            }
        );
        $('#endDate').datetimepicker({
            icons: {
                time: "fa fa-clock-o",
                date: "fa fa-calendar",
                up: "fa fa-chevron-up",
                down: "fa fa-chevron-down",
                previous: 'fa fa-chevron-left',
                next: 'fa fa-chevron-right',
                today: 'fa fa-screenshot',
                clear: 'fa fa-trash',
                close: 'fa fa-remove'
            }
            , format: "DD-MM-YYYY  HH:mm"
            , useCurrent: false

        });
        $("#startDate").on("dp.change", function (e) {
            $('#endDate').data("DateTimePicker").minDate(e.date);
            $scope.$apply(function () {
                $scope.newReservation.startDate = e.date.format("DD-MM-YYYY  HH:mm");
                $scope.calculateTotalPrice();

            })

        });
        $("#endDate").on("dp.change", function (e) {
            $('#startDate').data("DateTimePicker").maxDate(e.date);
            $scope.$apply(function () {
                $scope.newReservation.endDate = e.date.format("DD-MM-YYYY  HH:mm");
                $scope.calculateTotalPrice();
            })
        });
    });

    // date picker code end >>>


    // new reservation
    $scope.newReservation = {};
    $scope.newReservation.startDate = null;
    $scope.newReservation.endDate = null;
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

    $scope.calculateTotalPrice = function () {

        $scope.mStart = moment($scope.newReservation.startDate, "DD-MM-YYYY  HH:mm");
        $scope.mEnd = moment($scope.newReservation.endDate, "DD-MM-YYYY  HH:mm");

        $scope.dateSt = $scope.mStart.toDate();
        $scope.dateEnd = $scope.mEnd.toDate();

        $scope.dif = ($scope.dateEnd - $scope.dateSt) / 3.6e+6;
        $scope.newReservation.totalPrice = Math.ceil($scope.dif) * $scope.pricePerHour;


    };

    $scope.message = null;

    $scope.reserveVehicle = function () {
        $http.post(reservationsUrl, $scope.newReservation)
            .then(
                function success() {

                    $location.path("/");
                },
                function error() {

                    $scope.message = "Reservation failed, try again.";
                }
            );
    };

});

rentAVehicleApp.controller("vehicleBranchCtrl", function () {

});