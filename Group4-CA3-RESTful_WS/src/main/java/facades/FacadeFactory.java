package facades;

import java.lang.reflect.InvocationTargetException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
   Facade Factory class.
 **/
public class FacadeFactory 
{
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
    private static EntityManager em = emf.createEntityManager();

    public static <T> T createFacade(Class<T> facadeClass)
    {
        T facade = null;
        try 
        {
            facade = facadeClass.getDeclaredConstructor(EntityManager.class).newInstance(em);
        } 
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return facade;
    }
}