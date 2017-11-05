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
     * @param city City
     * @param street Street
     * @param zip Zip Code
     * @param description Description
     * @param fileName Filename
     * @return New Place entity.
     * @throws IOException
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPlace(@FormDataParam("city") String city, @FormDataParam("street") String street, @FormDataParam("zip") int zip, @FormDataParam("description") String description, @FormDataParam("rating") int rating, @FormDataParam("file") String fileName) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Place place = facade.addPlace(city, street, zip, fileName, description, rating);
        return gson.toJson(place);
    }
}