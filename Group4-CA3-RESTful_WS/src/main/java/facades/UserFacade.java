package facades;

import security.IUserFacade;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import security.IUser;
import security.PasswordStorage;

/**
 * User Facade class.
 */
public class UserFacade implements IUserFacade
{

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Gets User by ID.
     * @param id User Id.
     * @return User Entity.
     */
    @Override
    public IUser getUserByUserId(String id)
    {
        EntityManager em = getEntityManager();

        try
        {
            return em.find(User.class, id);
        }
        finally
        {
            em.close();
        }
    }

    /**
     * Authenticate User.
     * @param userName Username
     * @param password Password
     * @return User Auth status.
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        try {
            System.out.println("User Before:" + userName+", "+password);
            IUser user = getUserByUserId(userName);
            System.out.println("User After:" + user.getUserName()+", "+user.getPasswordHash());
            return user != null && PasswordStorage.verifyPassword(password, user.getPasswordHash()) ? user.getRolesAsStrings() : null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            throw new NotAuthorizedException("Invalid username or password", Response.Status.FORBIDDEN);
        }
    }

    /**
     * Creates new User.
     * @param user User entity.
     * @return User Entity.
     */
    public IUser createUser(User user){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    /**
     * Gets all users.
     * @return List of Users.
     */
    public List<IUser> getAllUsers(){
        EntityManager em = getEntityManager();
        List<IUser> userList = em.createQuery("Select c from USERS c").getResultList();
        return userList;
    }
}