/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.FacadeFactory;
import facades.UserFacade;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.IUser;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("demoall")
public class All {

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of A
   */
  public All() {
  }

  /**
   * Retrieves representation of an instance of rest.All
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getText() {
    return " {\"message\" : \"result for all\"}";
  }
  
  
  //TEST ENDPOINTS, DELETE WHEN DONE
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("name") String name) 
    {
        UserFacade facade = FacadeFactory.createFacade(UserFacade.class);
        Gson gson = new Gson();
        User user = (User) facade.getUser(name);
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return gson.toJson(user);
    }
    
    //TEST ENDPOINTS, DELETE WHEN DONE
    @DELETE
    @Path("delete/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        UserFacade facade = FacadeFactory.createFacade(UserFacade.class);
        try {
            facade.deleteUser(id);
        } catch (EntityNotFoundException e) {
            Response.status(Response.Status.NOT_FOUND);
        }
        return Response.status(200).build();
    }
    
    //TEST ENDPOINTS, DELETE WHEN DONE
    @PUT
    @Path("edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(@PathParam("id") int id, String content) {
        UserFacade facade = FacadeFactory.createFacade(UserFacade.class);
        Gson gson = new Gson();
        User user = gson.fromJson(content, User.class);
        if (user != null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        try {
            facade.editUser(user);
        } catch (EntityNotFoundException e) {
            Response.status(Response.Status.NOT_FOUND);
        }
        return Response.status(200).build();
    }
    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers()
    {
        UserFacade facade = FacadeFactory.createFacade(UserFacade.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<IUser> listOfUsers = facade.getAllUsers();

        return gson.toJson(listOfUsers);
      }
}
