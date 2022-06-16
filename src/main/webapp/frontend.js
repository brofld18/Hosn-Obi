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
    if (gameId != null && userId != null && inGame != true) {
        gameStarted().then(r => {
            window.open("game.html", "_self");
        })
    }
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

function Start() {
    startGame().then(r => {
        window.open("game.html", "_self");
    });

}

function SetGameManager(gameManager) {
    document.getElementById("Player1Lives").innerText = "Leben: " + gameManager.users[0].lives;
    document.getElementById("Player2Lives").innerText = "Leben: " + gameManager.users[1].lives;
    document.getElementById("Player3Lives").innerText = "Leben: " + gameManager.users[2].lives;
    document.getElementById("Player4Lives").innerText = "Leben: " + gameManager.users[3].lives;

    document.getElementById("Player1Name").innerText = gameManager.users[0].username;
    document.getElementById("Player2Name").innerText = gameManager.users[1].username;
    document.getElementById("Player3Name").innerText = gameManager.users[2].username;
    document.getElementById("Player4Name").innerText = gameManager.users[3].username;

    document.getElementById()
}