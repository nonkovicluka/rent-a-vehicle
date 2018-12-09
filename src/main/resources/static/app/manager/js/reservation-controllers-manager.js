rentAVehicleApp.controller("reservationsCtrl", function ($scope, $http, $location, $routeParams, AuthService) {

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

    $scope.reservationsByAgency = [];
    $scope.totalEarnings = 0;

    var baseUrlReservationsByAgency = "api/reservations/agencyReservations";
    var baseUrlAgencyTotalEarnings = "api/reservations/agencyTotalEarnings";

    var getReservationsByAgency = function () {


        var config = {params: {}};
        config.params.agencyId = $routeParams.agencyId;

        $http.get(baseUrlReservationsByAgency, config)
            .then(function success(data) {
                    $scope.reservationsByAgency = data.data;
                },
                function error(data) {

                });

    };

    var getAgencyTotalEarnings = function () {


        var config = {params: {}};
        config.params.agencyId = $routeParams.agencyId;

        $http.get(baseUrlAgencyTotalEarnings, config)
            .then(function success(data) {
                    $scope.totalEarnings = data.data;
                },
                function error(data) {

                });

    };

    getReservationsByAgency();
    getAgencyTotalEarnings();


    // sorting reservations

    $scope.propertyName = 'vehicleName';
    $scope.reverse = true;

    $scope.sortBy = function (propertyName) {
        $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
        $scope.propertyName = propertyName;
    };

    $scope.getSortClass = function (propertyName) {

        if ($scope.propertyName === propertyName) {
            return $scope.reverse ? 'fa fa-chevron-down' : 'fa fa-chevron-up';
        }
        return '';
    }

});