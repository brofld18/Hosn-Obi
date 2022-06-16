package at.kaindorf.htl.hosnobi.bl.api;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.db.GameDatabase;
import at.kaindorf.htl.hosnobi.bl.db.UserDatabase;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;
import at.kaindorf.htl.hosnobi.bl.exceptions.UserNotFoundException;
import at.kaindorf.htl.hosnobi.bl.states.HandingOutCardsState;
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import nonapi.io.github.classgraph.json.JSONSerializer;

import java.util.List;

@Path("/game")
public class GameResource {

    @Context
    private UriInfo context;

    @PUT
    @Path("/join")
    public Response joinGame(int userId, @QueryParam("gameId") @DefaultValue("-1") int gameId) {
        if(gameId == -1) return Response.status(Response.Status.BAD_REQUEST).build();
        if(!UserDatabase.getInstance().userExists(userId)) return Response.status(Response.Status.NOT_FOUND)
                .entity("User does not exist").build();
        try {
            GameDatabase.getInstance().addUserToGame(gameId, UserDatabase.getInstance().getUserById(userId));
            return Response.ok().build();
        } catch (MaxPlayersRechedException mpre) {
            return Response.status(Response.Status.NO_CONTENT).entity("Max players reached").build();
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).entity("Game does not exist").build();
        }
    }

    @PUT
    @Path("/leave")
    public Response leaveGame(int userId, @QueryParam("gameId") @DefaultValue("-1") int gameId) {
        try {
            GameDatabase.getInstance().RemoveUserFromGame(gameId, UserDatabase.getInstance().getUserById(userId));
            return Response.ok().build();
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).entity("Game not found").build();
        } catch (UserNotFoundException unfe) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not in game").build();
        }
    }

    @PUT
    @Path("/{gameId}/{userId}")
    @Consumes("application/json")
    public Response swapCards(@PathParam("gameId") @DefaultValue("-1") int gameId,
                              @PathParam("userId") @DefaultValue("-1") int userId, boolean[] swap) {
        if(swap.length != 3) return Response.status(Response.Status.BAD_REQUEST).build();
        try {
            GameManager game = GameDatabase.getInstance().getGameById(gameId);
            User user = null;
            for (User user1 :
                    game.getUsers()) {
                if(user1.getId() == userId) {
                    user = user1;
                    break;
                }
            }
            if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
            if(game.getGameState() instanceof HandingOutCardsState) ((HandingOutCardsState) game.getGameState())
                    .AddSwapCards(user, null);
            return Response.ok().build();
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{gameId}/startGame")
    public Response startGame(@PathParam("gameId") @DefaultValue("-1") int gameId) {
        try {
            GameManager game = GameDatabase.getInstance().getGameById(gameId);
            game.startGame();
            return Response.ok().build();
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{gameId}/startGame")
    public Response gameStarted(@PathParam("gameId") @DefaultValue("-1") int gameId) {
        try {
            GameManager game = GameDatabase.getInstance().getGameById(gameId);
            if(game.getGameState() != null) {
                return Response.ok(true).build();
            } else {
                return Response.ok(false).build();
            }
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createGame(int user) {
        try {
            int gameId = GameDatabase.getInstance().CreateNewGame(UserDatabase.getInstance().getUserById(user));
            UriBuilder uriBuilder = context.getAbsolutePathBuilder();
            uriBuilder.path("/" + gameId);

            return Response.created(uriBuilder.build()).entity(gameId).build();
        } catch (UserNotFoundException unfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{gameId}")
    @Produces("application/json")
    public Response getGame(@PathParam("gameId") @DefaultValue("-1") int gameId) {
        try {
            GameManager game = GameDatabase.getInstance().getGameById(gameId);
            return Response.ok(new Gson().toJson(game)).build();
        } catch (GameNotFoundException gnfe) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}