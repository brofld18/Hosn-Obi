let socket = null;
let offset = 0;

function startSocket() {
    socket = new WebSocket("ws://localhost:8080/Hosn-Obi-1.0-SNAPSHOT//websocket/game/" + gameId + "/" + userId);

    socket.onopen = function (e) {

    };

    socket.onmessage = function (event) {
        SetGameManager(JSON.parse(event.data));
    };

    socket.onclose = function (event) {
        if (event.wasClean) {
            alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
        } else {
            // e.g. server process killed or network down
            // event.code is usually 1006 in this case
            alert('[close] Connection died');
        }
    };

    socket.onerror = function (error) {
        alert(`[error] ${error.message}`);
    };
}

async function Block() {
    await socket.send("PlayerBlock");
}

async function NextPlayer() {
    await socket.send("NextPlayer");
}

async function CardsSwapped(cards) {
    if(cards[0] == 4 && cards[1] == 4) await socket.send("{\"0\": \"0\", \"1\": \"1\", \"2\": \"2\"}")
    else {
        socket.send(`{"${cards[0]-1}}": "${cards[1]-1}"`);
    }
}