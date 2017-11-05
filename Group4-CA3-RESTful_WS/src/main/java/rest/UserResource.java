<<<<<<< HEAD
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        POST {baseUrl}/api/user
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
        POST {baseUrl}/api/user/auth

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

=======
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

/**
 * REST Endpoint for Users.
 */
@Path("user")
@RolesAllowed("User")
public class UserResource
{
    private UserFacade facade;

    public UserResource()
    {
        facade = FacadeFactory.createFacade(UserFacade.class);
    }

    /**
     * POST {baseUrl}/user
     * @param json JSON POST Body
     * @return Created User JSON body.
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

    /**
     * POST {baseUrl}/user/auth
     * @param json JSON POST Body
     * @return Authenticated User JSON body.
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


>>>>>>> fb41157b655acc29e631d9b1e99d68fe64e5d031
}