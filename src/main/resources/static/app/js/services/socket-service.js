rentAVehicleApp.service('SocketService', function () {
    var stompClient = null;

    return {
        getSocketInstance: function () {
            var socket = new SockJS('/websocket');
            var stompClient = Stomp.over(socket);
            return stompClient
        },

        connect: function () {
            stompClient = this.getSocketInstance();
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/user/approval', function (user) {

                });

            });
        },

        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }
    }
});