package rest;

import com.google.gson.Gson;
import entity.User;
import facades.FacadeFactory;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
@RolesAllowed("User")
public class UserResource
{
    private UserFacade facade;
     
    public UserResource()
    {
        facade = FacadeFactory.createFacade(UserFacade.class);
    }

    /*
        Adds new user entry.
        @returns: User entity.
     */
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

    /*
        Authenticates user.
        @returns: User entity.
     */
    @POST
    @Path("auth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String loginUser(String json)
    {
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        facade.authenticateUser(user.getUserName(), user.getPasswordHash());
       
        return gson.toJson(user);
    }
 
    
}