rentAVehicleApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/html/agency/agency-search.html',
            controller: 'agencySearchCtrl'
        })
        .when('/home', {
            templateUrl: 'app/html/auth/home.html',
            controller: 'HomeController'
        })
        .when('/login', {
            templateUrl: 'app/html/auth/login.html',
            controller: 'LoginController'
        })
        .when('/users', {
            templateUrl: 'app/html/auth/users.html',
            controller: 'UsersController'
        })
        .when('/page-not-found', {
            templateUrl: 'app/html/auth/page-not-found.html',
            controller: 'PageNotFoundController'
        })
        .when('/access-denied', {
            templateUrl: 'app/html/auth/access-denied.html',
            controller: 'AccessDeniedController'
        })
        .when('/register', {
            templateUrl: 'app/html/auth/register.html',
            controller: 'RegisterController'
        })
        .when('/agencies', {
            templateUrl: "/app/html/agency/agency-search.html",
            controller: "agencySearchCtrl"
        })
        .when('/agencies/register', {
            templateUrl: "/app/manager/html/agency-registration.html",
            controller: "registerAgencyCtrl"
        })
        .when('/branches/register', {
            templateUrl: "/app/html/branch/branch-registration.html",
            controller: "registerBranchCtrl"
        })
        .when('/agencies/:agencyId/branches', {
            templateUrl: "/app/html/branch/agency-branches.html",
            controller: "branchByAgencyCtrl"
        })
        .when('/agencies/:agencyId/vehicles', {
            templateUrl: "/app/html/vehicle/vehicle-search.html",
            controller: "vehicleSearchCtrl"
        })
        .when('/agencies/:agencyId/vehicles/add', {
            templateUrl: "/app/manager/html/vehicle-registration.html",
            controller: "addVehicleCtrl"
        })
        .when('/agencies/:agencyId/vehicles/:vehicleId/reserve', {
            templateUrl: "/app/html/vehicle/vehicle-reservation.html",
            controller: "reserveVehicleCtrl"
        })
        .when('/my-agencies/:agencyId/manage/add-price-list', {
            templateUrl: "/app/manager/html/price-list-registration.html",
            controller: "addPriceList"
        })
        .when('/my-agencies/:agencyId/manage', {
            templateUrl: "/app/manager/html/agency-vehicle-manage.html",
            controller: "agencyVehicleCtrl"
        })
        .when('/my-agencies', {
            templateUrl: "/app/manager/html/my-agencies.html",
            controller: "myAgenciesCtrl"
        })
        .when('/my-agencies/:agencyId/reservations', {
            templateUrl: "/app/manager/html/reservation-search.html",
            controller: "reservationsCtrl"
        })
        .when('/agencies/:agencyId/rate', {
            templateUrl: "/app/html/agency/agency-rate.html",
            controller: "rateAgencyCtrl"
        })
        .otherwise({
            redirectTo: '/page-not-found'
        });
}]);