rentAVehicleApp.service("branchImagesService", function ($http, $location, $rootScope) {

    this.post = function (uploadUrl, files, branch) {
        var data = new FormData();

        for (var i = 0; i < files.length; i++) {
            data.append('branchImages', files[i]);
        }
        data.append("branchDTO", JSON.stringify(branch));
        $http.post(uploadUrl, data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            function success() {
                $location.path("/agencies/" + branch.agencyId + "/branches");
            },
            function error() {
                $rootScope.message = "Registration failed, please try again."

            }
        );
    }
});