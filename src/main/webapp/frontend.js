function fillPlayer(playerArr){
    document.getElementById("player1").innerText = playerArr[0];
    document.getElementById("player2").innerText = playerArr[1];
    document.getElementById("player3").innerText = playerArr[2];
    document.getElementById("player4").innerText = playerArr[3];
}

function setGameId(gameId){
    document.getElementById("gameId").innerText = gameId;
}

async function createLobby(username) {
    await createUser(username);
    await createGame();
    window.open("lobby.html", "_self");
}

async function joinLobby(gameId, username){
    await createUser(username);
    await joinGame(gameId);
    window.open("lobby.html", "_self");
}

function setSettings(){

}

function onLoad() {
    loadVariables();
}