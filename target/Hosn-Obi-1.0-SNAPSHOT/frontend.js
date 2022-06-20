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

function switchCards(cardNum){
    let cardSRC1 = "";
    let cardSRC2 = "";
    let cardID = "midCard" + cardNum;
    if(card1toggle == 1){
        cardSRC1 = document.getElementById('card1').src;
        cardSRC2 = document.getElementById(cardID).src;

        document.getElementById('card1').src = cardSRC2;
        document.getElementById(cardID).src = cardSRC1;
    }
    else if(card2toggle == 1){
        cardSRC1 = document.getElementById('card2').src;
        cardSRC2 = document.getElementById(cardID).src;

        document.getElementById('card2').src = cardSRC2;
        document.getElementById(cardID).src = cardSRC1;
    }
    else if(card3toggle == 1){
        cardSRC1 = document.getElementById('card3').src;
        cardSRC2 = document.getElementById(cardID).src;

        document.getElementById('card3').src = cardSRC2;
        document.getElementById(cardID).src = cardSRC1;
    }
    else {
        console.log("Select your own card first!");
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

    document.getElementById();
}

//Array[3] wird übergeben --> Source zum Karten Bild von der Mitte
// -:- Spielerkarten
//ich bekomme ID - ich gebe highlighting
//ich bekomme ID - gebe geblocked
//ich gette Username - ich zeige Spieler hat gewonnen
//ich bekomme Lebencounter Array[4] --> Stelle 0 Spieler selbst
//Funktion die zurückgibt welche 2 Karten getauscht wurden, als Index (Array mit 2 Stellen)