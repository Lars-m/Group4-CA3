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
    private String FILE_LOCATION = "";
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
     * Save File to the file system.
     * @param is Input Stream.
     * @param fileLocation File Location.
     * @throws IOException
     */
    private void saveFile(InputStream is, String fileLocation) throws IOException
    {
        String location = FILE_LOCATION + fileLocation;
        try (OutputStream os = new FileOutputStream(new File(location)))
        {
            byte[] buffer = new byte[256];
            int bytes = 0;
            while ((bytes = is.read(buffer)) != -1)
            {
                os.write(buffer, 0, bytes);
            }
        }
    }
}