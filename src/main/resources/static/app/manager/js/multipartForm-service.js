rentAVehicleApp.service("multipartForm", function ($http, $location, $routeParams, $rootScope) {

    this.post = function (uploadUrl, files, vehicle) {
        var data = new FormData();

        for (var i = 0; i < files.length; i++) {
            data.append('vehicleImages', files[i]);
        }
        data.append("vehicleDTO", JSON.stringify(vehicle));
        data.append("agencyId", $routeParams.agencyId);
        $http.post(uploadUrl, data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            function success() {
                $location.path("/my-agencies/" + $routeParams.agencyId + "/manage");
            },
            function error() {
                $rootScope.message = "Registration failed, please try again."

            }
        );
    }
});