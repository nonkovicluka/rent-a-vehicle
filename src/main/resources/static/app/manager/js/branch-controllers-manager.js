rentAVehicleApp.controller("registerBranchCtrl", function ($scope, $http, $location, AuthService, NgMap, branchImagesService) {

    $scope.user = AuthService.user;

    var redirect = function () {
        if (!$scope.user) {
            $location.path("/login");
        }
    };

    redirect();

    var baseUrlBranch = "/api/branches/addAll";
    var baseUrlAgency = "/api/agencies/" + $scope.user.id + "u";

    $scope.agencies = [];


    $scope.newBranch = {};
    $scope.newBranch.address = "";
    $scope.newBranch.phoneNumber = "";
    $scope.newBranch.agencyId = "";
    $scope.newBranch.latitude = "";
    $scope.newBranch.longitude = "";

    $scope.getCords = function () {
        NgMap.getGeoLocation($scope.newBranch.address).then(function (latlng) {
            $scope.newBranch.latitude = latlng.lat();
            $scope.newBranch.longitude = latlng.lng();

        });
    };

    var getAgencies = function () {

        $http.get(baseUrlAgency)
            .then(function success(data) {
                $scope.agencies = data.data;
            });

    };

    getAgencies();

    $scope.message = null;

    $scope.picFiles = [];

    $scope.deletePic = function (p) {

        $scope.picFiles.splice($scope.picFiles.indexOf(p), 1);

    };

    $scope.uploadAndSave = function () {
        branchImagesService.post(baseUrlBranch, $scope.picFiles, $scope.newBranch);
    };

});