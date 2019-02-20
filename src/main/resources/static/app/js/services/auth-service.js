// Creating the Angular Service for storing logged user details
rentAVehicleApp.service('AuthService', function () {
    return {
        getUser: function ()
        {
            if(localStorage.user)
            {
                return JSON.parse(localStorage.user);
            }
            return null;
        },
        removeUser: function() {
            delete localStorage.user;
            delete localStorage.token;
        }
    }
});
