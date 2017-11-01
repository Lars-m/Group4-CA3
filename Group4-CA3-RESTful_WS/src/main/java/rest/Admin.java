package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeFactory;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.IUser;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {
  private UserFacade userFacade;
  
  public Admin()
  {
    userFacade = FacadeFactory.createFacade(UserFacade.class);
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    return "{\"message\" : \"Hello Admin from server (call accesible by only authenticated ADMINS)\",\n"+"\"serverTime\": \""+now +"\"}"; 
  }
 
  @GET
  @Path("allUsers")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllUsers(){
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      List<IUser> listOfUsers = userFacade.getAllUsers();
      
      return gson.toJson(listOfUsers);
  } 
}
