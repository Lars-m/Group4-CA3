package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Place;
import facades.PlacesFacade;
import facades.FacadeFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.*;
import java.util.List;

/**
 REST {baseUrl}/api/place
 */
@Path("place")
public class PlaceResource 
{
    private PlacesFacade facade;

    //TODO: Change file location.
    private String FILE_LOCATION = "/Users/lukasjurgelionis/Documents/Development/Group4-CA3/Group4-CA3-WebApp/public/";
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

    /**
     * POST: {baseUrl}/api/place
     * @return New Place entity.
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPlace(String jsonContent) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Place place = facade.addPlace(gson.fromJson(jsonContent, entity.Place.class));
        return gson.toJson(place);
    }
}