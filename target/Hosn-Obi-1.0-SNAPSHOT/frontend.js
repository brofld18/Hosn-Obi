let card1toggle = -1;
let card2toggle = -1;
let card3toggle = -1;
let cardsSwitched = 0;
let blocked = 0;

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

function setPlayerCards(cards){
    document.getElementById('card1').src = cards[0];
    document.getElementById('card2').src = cards[1];
    document.getElementById('card3').src = cards[2];
}

function setMidCards(cards){
    document.getElementById('midCard1').src = cards[0];
    document.getElementById('midCard2').src = cards[1];
    document.getElementById('midCard3').src = cards[2];
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
    let switchedCards = [];

    if(cardsSwitched == 0){
        if(card1toggle == 1){
            cardSRC1 = document.getElementById('card1').src;
            cardSRC2 = document.getElementById(cardID).src;

            document.getElementById('card1').src = cardSRC2;
            document.getElementById(cardID).src = cardSRC1;
            cardsSwitched = 1;

            switchedCards[0] = 1;
            switchedCards[1] = cardNum;

            return switchedCards;
        }
        else if(card2toggle == 1){
            cardSRC1 = document.getElementById('card2').src;
            cardSRC2 = document.getElementById(cardID).src;

            document.getElementById('card2').src = cardSRC2;
            document.getElementById(cardID).src = cardSRC1;
            cardsSwitched = 1;

            switchedCards[0] = 2;
            switchedCards[1] = cardNum;

            return switchedCards;
        }
        else if(card3toggle == 1){
            cardSRC1 = document.getElementById('card3').src;
            cardSRC2 = document.getElementById(cardID).src;

            document.getElementById('card3').src = cardSRC2;
            document.getElementById(cardID).src = cardSRC1;
            cardsSwitched = 1;

            switchedCards[0] = 3;
            switchedCards[1] = cardNum;

            return switchedCards;
        }
        else {
            console.log("Select your own card first!");
        }
    }
    else {
        console.log("Cards already switched.");
    }
}

function switchAll(){
    let cardSRC1 = "";
    let cardSRC2 = "";
    let switchedCards = [];

    if (cardsSwitched == 0){
        cardSRC1 = document.getElementById('card1').src;
        cardSRC2 = document.getElementById('midCard1').src;

        document.getElementById('card1').src = cardSRC2;
        document.getElementById('midCard1').src = cardSRC1;


        cardSRC1 = document.getElementById('card2').src;
        cardSRC2 = document.getElementById('midCard2').src;

        document.getElementById('card2').src = cardSRC2;
        document.getElementById('midCard2').src = cardSRC1;


        cardSRC1 = document.getElementById('card3').src;
        cardSRC2 = document.getElementById('midCard3').src;

        document.getElementById('card3').src = cardSRC2;
        document.getElementById('midCard3').src = cardSRC1;


        cardsSwitched = 1;


        switchedCards[0] = 4;
        switchedCards[1] = 4;

        return switchedCards; //[4; 4] --> alle Karten werden getauscht
    }
    else {
        console.log("Cards already switched.");
    }
}

function nextOne(){

}

function block(){
    nextOne();
    blocked = 1;
    document.getElementById('blocked1').innerText = ' - gesperrt';
    document.getElementById('player1').style.color = "red";
}

function playerPlaying(id){
    document.getElementById("card1").style.boxShadow = "0px 0px 15px 5px #606060";
    document.getElementById("card2").style.boxShadow = "0px 0px 15px 5px #606060";
    document.getElementById("card3").style.boxShadow = "0px 0px 15px 5px #606060";

    document.getElementById("player2Card1").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player2Card2").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player2Card3").style.boxShadow = "0px 0px 0px 0px #ffffff";

    document.getElementById("player3Card1").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player3Card2").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player3Card3").style.boxShadow = "0px 0px 0px 0px #ffffff";

    document.getElementById("player4Card1").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player4Card2").style.boxShadow = "0px 0px 0px 0px #ffffff";
    document.getElementById("player4Card3").style.boxShadow = "0px 0px 0px 0px #ffffff";


    if(id == 1){
        document.getElementById("card1").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("card2").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("card3").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    if(id == 2){
        document.getElementById("player2Card1").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player2Card2").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player2Card3").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    if(id == 3){
        document.getElementById("player3Card1").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player3Card2").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player3Card3").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
    if(id == 4){
        document.getElementById("player4Card1").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player4Card2").style.boxShadow = "0px 0px 15px 5px #ffffff";
        document.getElementById("player4Card3").style.boxShadow = "0px 0px 15px 5px #ffffff";
    }
}

function playerBlocked(id){
    if(id == 1){
        document.getElementById('blocked1').innerText = ' - gesperrt';
        document.getElementById('player1').style.color = "red";
    }
    if(id == 2){
        document.getElementById('blocked2').innerText = ' - gesperrt';
        document.getElementById('player2').style.color = "red";
    }
    if(id == 3){
        document.getElementById('blocked3').innerText = ' - gesperrt';
        document.getElementById('player3').style.color = "red";
    }
    if(id == 4){
        document.getElementById('blocked4').innerText = ' - gesperrt';
        document.getElementById('player4').style.color = "red";
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

//ich gette Username - ich zeige Spieler hat gewonnen --> Moritz nach brunch
//ich bekomme Lebencounter Array[4] --> Stelle 0 Spieler selbst --> Moritz nach brunch

//Wenn man selbst wieder dran ist die Variable cardsSwitched wieder auf 0 setzen. --> Flo bitte machen