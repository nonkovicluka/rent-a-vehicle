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
