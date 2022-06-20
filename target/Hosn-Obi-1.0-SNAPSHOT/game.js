let socket = new WebSocket("ws://localhost:8080/Hosn-Obi-1.0-SNAPSHOT/websocket/game/" + gameId + "/" + userId);

socket.onopen = function(e) {

};

socket.onmessage = function(event) {
    SetGameManager(JSON.parse(event));
};

socket.onclose = function(event) {
    if (event.wasClean) {
        alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        // e.g. server process killed or network down
        // event.code is usually 1006 in this case
        alert('[close] Connection died');
    }
};

socket.onerror = function(error) {
    alert(`[error] ${error.message}`);
};