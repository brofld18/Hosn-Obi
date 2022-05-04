function fillPlayer(playerArr){
    document.getElementById("player1").innerText(playerArr[0]);
    document.getElementById("player2").innerText(playerArr[1]);
    document.getElementById("player3").innerText(playerArr[2]);
    document.getElementById("player4").innerText(playerArr[3]);
}

function setGameId(gameId){
    document.getElementById("gameId").innerText(gameId);
}

function createLobby(){
    createGame();
}

function joinLobby(gameId){
    joinGame(gameId);
    window.open("lobby.html", "_self");
}

function setSettings(){

}