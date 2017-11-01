package rest;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Address;
import facades.AddressFacade;
import facades.FacadeFactory;
import java.util.List;

@Path("address")
public class AddressResource 
{
    private AddressFacade facade;
     
    public AddressResource()
{
    facade = FacadeFactory.createFacade(AddressFacade.class);
}
    
   /*
    Gets the user
    */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getPlaces()
  {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      List<Address> listOfAddresses = facade.getAllAddresses();

      return gson.toJson(listOfAddresses);
  }
 
}