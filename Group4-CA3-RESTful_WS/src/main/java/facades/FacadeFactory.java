<<<<<<< HEAD
package facades;

import java.lang.reflect.InvocationTargetException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lukasjurgelionis
 */
public class FacadeFactory 
{
    
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
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
=======
package facades;

import java.lang.reflect.InvocationTargetException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Facade Factory.
 */
public class FacadeFactory 
{
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
    private static EntityManager em = emf.createEntityManager();

    /**
     * Creates new facade instance.
     * @param facadeClass Facade class.
     * @param <T> Type.
     * @return Facade instance.
     */
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
>>>>>>> fb41157b655acc29e631d9b1e99d68fe64e5d031
}
