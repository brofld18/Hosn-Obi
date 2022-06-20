var userId = -1;
var gameId = -1;
var usernames = ["", "", "", ""];
var inGame = false;

async function createUser(username) {
    await fetch('./api/user', {
        method: "POST",
        body: username
    }).then(response => {
        if (response.status != 201) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(data => {
        userId = data.id
    })
        .catch(error => alert(error));
    sessionStorage.setItem("userId", userId);
}

async function createGame() {
    if (userId == -1) throw new Error("You first have to create a User!");
    await fetch('./api/game', {
        method: "POST",
        body: userId,
    }).then(response => {
        if (response.status != 201) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(data => gameId = data)
        .catch(error => alert(error));
    sessionStorage.setItem("gameId", gameId);
    await getGame();
}

async function joinGame(GameId) {
    if(userId == -1) throw new Error("You first have to create a User!");
    await fetch('./api/game/join/?gameId=' + GameId, {
        method: "PUT",
        body: userId
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
        gameId = GameId;
    }).catch(error => alert(error));
    sessionStorage.setItem("gameId", gameId);
    await getGame();
}

async function leaveGame() {
    if(userId == -1) throw new Error("You first have to create a User!");
    if(gameId == -1) throw new Error("You aren't in a Game!");
    await fetch('./api/game/leave?gameId=' + gameId, {
        method: "PUT",
        body: userId
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
    }).catch(error => alert(error));
}

async function getGame() {
    await fetch('./api/game/' + gameId, {
        method: "GET",
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(data => {
        for(i = 0; i < data.users.length; i++) {
            if(data.users[i] != null) {
                usernames[i] = data.users[i].username;
            } else {
                usernames[i] = "";
            }
        }
    }).catch(error => alert(error));
    sessionStorage.setItem("usernames", usernames);
    try {
        fillPlayer(usernames);
    } catch {}
}

async function loadVariables() {
    gameId = sessionStorage.getItem("gameId");
    userId = sessionStorage.getItem("userId");
    usernames = sessionStorage.getItem("usernames").split(",");
    inGame = sessionStorage.getItem("inGame");
    try {
        await getGame();
        fillPlayer(usernames);
        setGameId(gameId);
    } catch {}
}

async function startGame() {
    await fetch('./api/game/' + gameId + "/startGame", {
        method: "PUT"
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
    }).catch(error => alert(error));
}

async function gameStarted() {
    await fetch('./api/game/' + gameId + "/startGame", {
        method: "GET"
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(async data => {
        if (data) return
        else {
            await new Promise(resolve => setTimeout(resolve, 2000));
            await getGame();
            await gameStarted();
            sessionStorage.setItem("inGame", "true");
        }
    }).catch(error => alert(error));
}