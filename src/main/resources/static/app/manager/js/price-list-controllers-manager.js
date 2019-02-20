rentAVehicleApp.controller("addPriceList", function ($scope, $http, $location, $routeParams, AuthService) {

    $scope.user = AuthService.getUser();
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
                , format: "DD-MM-YYYY"

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
            , format: "DD-MM-YYYY"
            , useCurrent: false

        });
        $("#startDate").on("dp.change", function (e) {
            $('#endDate').data("DateTimePicker").minDate(e.date);
            $scope.$apply(function () {
                $scope.newPriceList.startDate = e.date.format("DD-MM-YYYY");

            })

        });
        $("#endDate").on("dp.change", function (e) {
            $('#startDate').data("DateTimePicker").maxDate(e.date);
            $scope.$apply(function () {
                $scope.newPriceList.endDate = e.date.format("DD-MM-YYYY");
            })
        });
    });

    // date picker code end >>>


});