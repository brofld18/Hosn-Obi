let card1toggle = -1;
let card2toggle = -1;
let card3toggle = -1;
let cardsSwitched = 0;
let blocked = 0;
let roundEnd = 0;

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
    document.getElementById('pointsAndState1').innerText = ' - gesperrt';
    document.getElementById('pointsAndState1').style.color = "red";
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
        document.getElementById('pointsAndState1').innerText = ' - gesperrt';
        document.getElementById('pointsAndState1').style.color = "red";
        document.getElementById('player1').style.color = "red";
    }
    if(id == 2){
        document.getElementById('pointsAndState2').innerText = ' - gesperrt';
        document.getElementById('pointsAndState2').style.color = "red";
        document.getElementById('player2').style.color = "red";
    }
    if(id == 3){
        document.getElementById('pointsAndState3').innerText = ' - gesperrt';
        document.getElementById('pointsAndState3').style.color = "red";
        document.getElementById('player3').style.color = "red";
    }
    if(id == 4){
        document.getElementById('pointsAndState4').innerText = ' - gesperrt';
        document.getElementById('pointsAndState4').style.color = "red";
        document.getElementById('player4').style.color = "red";
    }
}

function lifeCounter(players){
    document.getElementById("player1lifes").innerText = players[0].lives;
    document.getElementById("player2lifes").innerText = players[1].lives;
    document.getElementById("player3lifes").innerText = players[2].lives;
    document.getElementById("player4lifes").innerText = players[3].lives;
}

function endRound(points){
    document.getElementById("pointsAndState1").innerText = ' - Punkte: ' + points[0];
    document.getElementById("pointsAndState1").style.color = "white";
    document.getElementById("pointsAndState2").innerText = ' - Punkte: ' + points[1];
    document.getElementById("pointsAndState2").style.color = "white";
    document.getElementById("pointsAndState3").innerText = ' - Punkte: ' + points[2];
    document.getElementById("pointsAndState3").style.color = "white";
    document.getElementById("pointsAndState4").innerText = ' - Punkte: ' + points[3];
    document.getElementById("pointsAndState4").style.color = "white";

    let highest = points[0];
    let highestID = 1;
    let lowest = points[0];
    let lowestID = 1;

    for(i = 1; i < points.length; i++) {
        if(points[i] > highest) {
            highest = points[i];
            highestID = i+1;
        }
        if(points[i] < lowest){
            lowest = points[i];
            lowestID = i+1;
        }
    }

    let highestPlayer = 'player' + highestID;
    let highestPoints = 'pointsAndState' + highestID;
    let lowestPlayer = 'player' + lowestID;
    let lowestPoints = 'pointsAndState' + lowestID;

    document.getElementById(highestPlayer).style.color = "green";
    document.getElementById(highestPoints).style.color = "green";
    document.getElementById(lowestPlayer).style.color = "red";
    document.getElementById(lowestPoints).style.color = "red";
}

function newRound(players){
    roundEnd = 0;
    document.getElementById("pointsAndState1").innerText = '';
    document.getElementById("pointsAndState2").innerText = '';
    document.getElementById("pointsAndState3").innerText = '';
    document.getElementById("pointsAndState4").innerText = '';

    lifeCounter(players);
}

function Start() {
    startGame().then(r => {
        window.open("game.html", "_self");
    });

}

function SetGameManager(gameManager) {
    newRound(gameManager.users);

    /*
    document.getElementById("Player1Lives").innerText = "Leben: " + gameManager.users[0].lives;
    document.getElementById("Player2Lives").innerText = "Leben: " + gameManager.users[1].lives;
    document.getElementById("Player3Lives").innerText = "Leben: " + gameManager.users[2].lives;
    document.getElementById("Player4Lives").innerText = "Leben: " + gameManager.users[3].lives;

    document.getElementById("Player1Name").innerText = gameManager.users[0].username;
    document.getElementById("Player2Name").innerText = gameManager.users[1].username;
    document.getElementById("Player3Name").innerText = gameManager.users[2].username;
    document.getElementById("Player4Name").innerText = gameManager.users[3].username;
    */
}

//ich gette Username - ich zeige Spieler hat gewonnen --> habe dafür Punkte zeigen eingebaut + farbliche Hervorhebung der meisten und wenigsten Punkte
//ich bekomme Lebencounter Array[4] --> Stelle 0 Spieler selbst --> Flo Kontrolle

//Wenn man selbst wieder dran ist die Variable cardsSwitched wieder auf 0 setzen. --> Flo bitte machen