package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.FacadeFactory;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.IUser;


/**
 * {baseUrl}/api/admin
 */
@Path("admin")
@RolesAllowed("Admin")
public class Admin
{
    private UserFacade userFacade;

    public Admin()
    {
        userFacade = FacadeFactory.createFacade(UserFacade.class);
    }

    /**
     * Dummy class.
     * @return JSON Response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething()
    {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());

        return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n"+"\"serverTime\": \""+now +"\"}";
    }

    /**
     * GET: {baseUrl}/api/admin/users
     * @return: List Of Users
     */
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<IUser> listOfUsers = userFacade.getAllUsers();

        return gson.toJson(listOfUsers);
    }

    /**
     * GET: {baseUrl}/api/admin/{name}
     * @param name Username
     * @return User
     */
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("name") String name)
    {
        Gson gson = new Gson();
        User user = (User) userFacade.getUserByUserId(name);

        if(user == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return gson.toJson(user);
    }

    /**
     * DELETE: {baseUrl}/api/admin/delete/{id}
     * @param id User ID.
     * @return HttpStatusCode
     */
    @DELETE
    @Path("delete/{id}")
    public Response deleteUser(@PathParam("id") int id)
    {
        try
        {
            userFacade.deleteUser(id);
        }
        catch (EntityNotFoundException e)
        {
            Response.status(Response.Status.NOT_FOUND);
        }

        return Response.status(200).build();
    }

    /**
     * PUT: {baseUrl}/api/admin/edit/{id}
     * @param id User ID.
     * @param content JSON Body.
     * @return HttpsStatusCode
     */
    @PUT
    @Path("edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(@PathParam("id") int id, String content)
    {
        Gson gson = new Gson();
        User user = gson.fromJson(content, User.class);

        if (user != null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        try
        {
            userFacade.editUser(user);
        }
        catch (EntityNotFoundException e)
        {
            Response.status(Response.Status.NOT_FOUND);
        }

        return Response.status(200).build();
    }
}
