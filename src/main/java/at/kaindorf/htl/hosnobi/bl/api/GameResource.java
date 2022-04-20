package at.kaindorf.htl.hosnobi.bl.api;

import jakarta.ws.rs.*;

@Path("/game")
public class GameResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}