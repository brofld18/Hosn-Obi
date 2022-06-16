package at.kaindorf.htl.hosnobi.bl.api.websockets.game;


import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.db.GameDatabase;
import at.kaindorf.htl.hosnobi.bl.db.UserDatabase;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;
import at.kaindorf.htl.hosnobi.bl.exceptions.UserNotFoundException;
import at.kaindorf.htl.hosnobi.bl.states.HandingOutCardsState;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import nonapi.io.github.classgraph.json.JSONDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocket/game/{gameId}/{userId}"/*,
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class*/)
public class GameEndpoint {

    public GameEndpoint() {}
    private Session session;
    private static Map<Integer, List<GameEndpoint>> gameEndpoints
            = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("gameId") int gameId, @PathParam("userId") int userId)
            throws IOException {
        this.session = session;
        try {
            UserDatabase.getInstance().getUserById(userId).setLoaded(true);
            GameManager gameManager = GameDatabase.getInstance().getGameById(gameId);
            gameManager.getGameState().PlayersLoaded();
            if(!gameEndpoints.containsKey(gameId))
                gameEndpoints.put(gameId, new ArrayList<>());
            gameEndpoints.get(gameId).add(this);
            try {
                broadcast(gameManager);
            } catch (EncodeException e) {
                throw new RuntimeException(e);
            }
        } catch (GameNotFoundException gnfe) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "There is no game with the id " + gameId));
        } catch (UserNotFoundException unfe) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "There is no user with the id " + gameId));
        } catch (IllegalStateMethodCallException e) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Game already in progress"));
        }
    }

    @OnMessage
    public void onMessage(@PathParam("gameId") int gameId,
                          @PathParam("userId") int userId, String message) throws IOException, EncodeException {
        try {
            UserDatabase.getInstance().getUserById(userId);
            GameManager gameManager = GameDatabase.getInstance().getGameById(gameId);
            if(gameManager.getGameState() instanceof HandingOutCardsState) return;
            if(userId == gameManager.getUsers()[gameManager.getGameState().currentPlayer].getId()) {
                switch (message) {
                    case "NextPlayer":
                        gameManager.getGameState().NextPlayer();
                        broadcast(gameManager);
                        break;
                    case "PlayerBlock":
                        gameManager.getGameState().PlayerBlock();
                        broadcast(gameManager);
                        break;
                    default:
                        try {
                            gameManager.getGameState().ChooseCard(JSONDeserializer.deserializeObject(new HashMap<Integer, Integer>().getClass(), message));
                            broadcast(gameManager);
                        } catch (IllegalArgumentException iae) {
                            return;
                        }
                }
            } else {
                return;
            }
            try {
                broadcast(gameManager);
            } catch (EncodeException e) {
                throw new RuntimeException(e);
            }
        } catch (GameNotFoundException gnfe) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "There is no game with the id " + gameId));
        } catch (UserNotFoundException unfe) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "There is no user with the id " + gameId));
        } catch (IllegalStateMethodCallException e) {
            return;
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("Closed");
        gameEndpoints.forEach((integer, gameEndpoints1) -> {
            int index = -1;
            for (int i = 0; i < gameEndpoints1.size(); i++) {
                if(gameEndpoints1.get(i).session.getId() == session.getId()) {
                    index = i;
                    break;
                }
            }
            if(index != -1) gameEndpoints1.remove(index);
        });
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    public static void broadcast(GameManager game)
            throws IOException, EncodeException {

        gameEndpoints.forEach((gameId, gameEndpoints1) -> {
            if(gameId == game.getGameId()) {
                gameEndpoints1.forEach(endpoint -> {
                    synchronized (endpoint) {
                        try {
                            endpoint.session.getBasicRemote().
                                    sendObject(game);
                        } catch (IOException | EncodeException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

/*@Data
@AllArgsConstructor
@NoArgsConstructor
class Message {
    private String from;
    private String to;
    private String content;
}

class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String s) throws DecodeException {
        return JSONDeserializer.deserializeObject(Message.class, s);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }
}

class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        return JSONSerializer.serializeObject(message);
    }
}*/