package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Place;
import facades.PlacesFacade;
import facades.FacadeFactory;
import java.util.List;

/**
 REST {baseUrl}/api/place
 */
@Path("place")
public class PlaceResource 
{
    private PlacesFacade facade;
     
    public PlaceResource()
{
    facade = FacadeFactory.createFacade(PlacesFacade.class);
}

    /**
     * GET: {baseUrl}/api/place
     * @return List of Places.
     */
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      public String getPlaces()
      {
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          List<Place> listOfPlaces = facade.getAllPlaces();

          return gson.toJson(listOfPlaces);
      }
}