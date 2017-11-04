/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.User;
import facades.FacadeFactory;
import facades.UserFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Sean
 */
@Path("register")
public class RegisterResource {
    private UserFacade facade;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
        facade = FacadeFactory.createFacade(UserFacade.class);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String json)
    {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        facade.createUser(user);

        return gson.toJson(user);
    }
}
