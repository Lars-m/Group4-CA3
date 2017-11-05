/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import facades.FacadeFactory;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.PasswordStorage;

/**
 * REST Web Service
 *
 * @author Sean
 */
@Path("register")
public class RegisterResource {
    private UserFacade facade;
    private Gson gson;
    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
        EntityManagerFactory emf = FacadeFactory.emf;
        UserFacade facade = new UserFacade(emf);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String gt() 
    {
        return "{\"REG\" : \"USEr\"}";
    }
  
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(String jsonString) throws PasswordStorage.CannotPerformOperationException
    {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        EntityManagerFactory emf = FacadeFactory.emf;
        UserFacade facade = new UserFacade(emf);
        facade.createUser(new User(username, password));
        JsonObject responseJson = new JsonObject();
        return Response.ok(new Gson().toJson(responseJson)).build();
    }
}
