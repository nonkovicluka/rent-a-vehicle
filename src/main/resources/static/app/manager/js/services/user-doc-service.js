rentAVehicleApp.service("userDoc", function ($http, $location, $routeParams, $rootScope, SocketService) {

    this.post = function (uploadUrl, doc, appUser, userRole) {
        var data = new FormData();

        data.append('doc', doc);
        data.append("userJson", JSON.stringify(appUser));
        data.append("userRole", userRole);

        $http.post(uploadUrl, data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            function success(userDTO) {
                var stompClient = SocketService.getSocketInstance();
                stompClient.connect({}, function (frame) {
                    console.log('Connected: ' + frame);
                    stompClient.send("/app/register", {}, JSON.stringify(userDTO.data));
                });

                $location.path("/");
            },
            function error() {
                $rootScope.message = "Registration failed, please try again."

            }
        );
    }
});