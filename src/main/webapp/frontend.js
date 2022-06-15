let card1toggle = -1;
let card2toggle = -1;
let card3toggle = -1;

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

function cardClicked(cardNum){

    if(cardNum == 1){
        card1toggle = card1toggle * (-1);
        card2toggle = -1;
        card3toggle = -1;
    }

    if(cardNum == 2){
        card1toggle = -1;
        card2toggle = card2toggle * (-1);
        card3toggle = -1;
    }

    if(cardNum == 3){
        card1toggle = -1;
        card2toggle = -1;
        card3toggle = card3toggle * (-1);
    }

    if(card1toggle == 1){
        document.getElementById("card1").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    else {
        document.getElementById("card1").style.boxShadow = "0px 0px 15px 5px #606060";
    }

    if(card2toggle == 1){
        document.getElementById("card2").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    else {
        document.getElementById("card2").style.boxShadow = "0px 0px 15px 5px #606060";
    }

    if(card3toggle == 1){
        document.getElementById("card3").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    else {
        document.getElementById("card3").style.boxShadow = "0px 0px 15px 5px #606060";
    }
}