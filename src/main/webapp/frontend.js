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

async function joinLobby(gameId){
    await joinGame(gameId);
    window.open("lobby.html", "_self");
}

function setSettings(){

}

async function onLoad() {
    await loadVariables();
}

async function enterJoinLobbyScreen(username) {
    await createUser(username);
    window.open("joinGame.html", "_self");
}