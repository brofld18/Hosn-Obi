package at.kaindorf.htl.hosnobi.bl.api;

import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.db.GameDatabase;
import at.kaindorf.htl.hosnobi.bl.db.UserDatabase;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/user")
public class UserResource {

    @Context
    private UriInfo context;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getUser(@PathParam("id") @DefaultValue("-1") int id) {
        if(id == -1) return Response.noContent().build();
        if(!UserDatabase.getInstance().userExists(id)) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(UserDatabase.getInstance().getUserById(id)).build();
    }

    @POST
    public Response createUser(String username, @QueryParam("GameId") @DefaultValue("-1") int gameId) {
        if(username == null || username.isEmpty()) {
            return Response.noContent().build();
        }
        User user = UserDatabase.getInstance().newUser(username);
        if(gameId != -1) {
            try {
                GameDatabase.getInstance().addUserToGame(gameId, user);
            } catch (MaxPlayersRechedException mpre) {
                return Response.status(Response.Status.CONFLICT).entity("Max users for game reached.").build();
            } catch (GameNotFoundException gnfe) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        UriBuilder uriBuilder = context.getAbsolutePathBuilder();
        uriBuilder.path("/" + user.getId());
        return Response.created(uriBuilder.build()).entity(user).build();
    }
}