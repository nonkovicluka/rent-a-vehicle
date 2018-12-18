rentAVehicleApp.service("agencyLogo", function ($http, $location, $routeParams, $rootScope) {

    this.post = function (uploadUrl, logo, agency) {
        var data = new FormData();

        data.append('logo', logo);
        data.append("agencyDTO", JSON.stringify(agency));

        $http.post(uploadUrl, data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            function success() {
                $location.path("/my-agencies");
            },
            function error() {
                $rootScope.message = "Registration failed, please try again."

            }
        );
    }
});