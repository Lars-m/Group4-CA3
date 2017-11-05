package rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.omg.CORBA.portable.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * {baseUrl}/image
 */
public class ImageResource
{
    //TODO: Change before deploying.
    private String FILE_LOCATION = "/Users/lukasjurgelionis/Documents/Development/Group4-CA3/Group4-CA3-WebApp/public/";

    /**
     * POST: {baseUrl}/image
     * @param file File
     * @param fileDisposition File Deetails
     * @return Response.
     * @throws IOException
     */
    @Path("file")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream file, @FormDataParam("file") FormDataContentDisposition fileDisposition) throws IOException
    {
        String fileName = fileDisposition.getFileName();
        saveFile(file, fileName);

        return Response.ok().build();
    }

    /**
     * Saves file in the file system.
     * @param is Input Stream
     * @param fileLocation File Location
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
