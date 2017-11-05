package rest;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *  Application Config class.
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application
{

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources)
    {
        resources.add(cors.CorsRequestFilter.class);
        resources.add(MultiPartFeature.class);
        resources.add(cors.CorsResponseFilter.class);
        resources.add(httpErrors.GenericExceptionMapper.class);
        resources.add(httpErrors.NotFoundExceptionMapper.class);
        resources.add(rest.Admin.class);
        resources.add(rest.All.class);
        resources.add(rest.PlaceResource.class);
        resources.add(rest.UserResource.class);
        resources.add(rest.ImageResource.class);
        resources.add(security.JWTAuthenticationFilter.class);
        resources.add(security.Login.class);
        resources.add(security.NotAuthorizedExceptionMapper.class);
        resources.add(security.RolesAllowedFilter.class);
    }

}