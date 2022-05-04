var userId = -1;
var gameId = -1;
var usernames = ["", "", "", ""];

function createUser(username) {
    fetch('./api/user', {
        method: "POST",
        body: username
    }).then(response => {
        if (response.status != 201) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(data => userId = data.Users[0].id)
        .catch(error => alert(error));
}

function createGame() {
    if(id == -1) throw new Error("You first have to create a User!");
    fetch('./api/game', {
        method: "POST",
        body: userId,
    }).then(response => {
        if (response.status != 201) {
            throw new Error(response.status + ": " + response.statusText);
        }
        gameId = response.body;
        setGameId(gameId);
    }).catch(error => alert(error));
}

function joinGame(GameId) {
    if(userId == -1) throw new Error("You first have to create a User!");
    fetch('./api/game/join?gameId=' + GameId, {
        method: "PUT",
        body: userId
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
        gameId = GameId;
        setGameId(gameId);
    }).catch(error => alert(error));
}

function leaveGame() {
    if(userId == -1) throw new Error("You first have to create a User!");
    if(gameId == -1) throw new Error("You aren't in a Game!");
    fetch('./api/game/leave?gameId=' + gameId, {
        method: "PUT",
        body: userId
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
    }).catch(error => alert(error));
}

function getGame() {
    fetch('./api/game?gameId=' + gameId, {
        method: "GET",
    }).then(response => {
        if (response.status != 200) {
            throw new Error(response.status + ": " + response.statusText);
        }
        return response.json();
    }).then(data => {
        for(i = 0; i < data.length; i++) {
            if(data.Users[i] != null) {
                usernames[i] = data.Users[i].username;
            } else {
                usernames[i] = "";
            }
        }
    }).catch(error => alert(error));
    fillPlayer(usernames);
}