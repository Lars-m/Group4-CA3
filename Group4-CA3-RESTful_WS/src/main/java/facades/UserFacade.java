package facades;

import entity.Role;
import security.IUserFacade;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import security.IUser;
import security.PasswordStorage;

/**
 * User Facade.
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
     * Gets user by Id.
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
     * Gets User by Username
     * @param username Username
     * @return User Entity.
     */
    public User getUser(String username)
    {
        EntityManager em = getEntityManager();
        Query sqlQuery = em.createQuery("SELECT c FROM SEED_USER c WHERE c.userName = :username", User.class)
                .setParameter("username", username);

        return (User) sqlQuery.getSingleResult();
    }

    /**
     * Authenticate User.
     * @param userName Username
     * @param password Password
     * @return User
     */
    @Override
    public List<String> authenticateUser(String userName, String password)
    {
        try
        {
            System.out.println("User Before:" + userName+", "+password);
            IUser user = getUserByUserId(userName);
            System.out.println("User After:" + user.getUserName()+", "+user.getPasswordHash());

            return user != null && PasswordStorage.verifyPassword(password, user.getPasswordHash()) ? user.getRolesAsStrings() : null;

        }
        catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex)
        {
            throw new NotAuthorizedException("Invalid username or password", Response.Status.FORBIDDEN);
        }
    }

    /**
     * Creates new user.
     * @param user User entity.
     * @return User entity.
     */
    public IUser createUser(User user)
    {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        Role role = new Role("User");
        em.persist(user);
        user.addRole(role);
        em.getTransaction().commit();

        return user;
    }

    /**
     * Gets all users.
     * @return List of Users.
     */
    public List<IUser> getAllUsers()
    {
        EntityManager em = getEntityManager();
        List<IUser> userList = em.createQuery("Select c from SEED_USER c").getResultList();

        return userList;
    }

    /**
     * Deletes User.
     * @param userId User Id
     * @return Deleted User.
     * @throws EntityNotFoundException
     */
    public User deleteUser(int userId) throws EntityNotFoundException
    {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);

        if (user == null)
        {
            throw new EntityNotFoundException("User: "+userId+" missing");
        }

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();

        return user;
    }

    /**
     * Edits the user.
     * @param user User.
     * @return User entity.
     * @throws EntityNotFoundException
     */
    public User editUser(User user) throws EntityNotFoundException
    {
        EntityManager em = emf.createEntityManager();
        User editUser = em.find(User.class, user.getUserName());

        if (editUser == null)
        {
            throw new EntityNotFoundException("User: "+user.getUserName()+" missing");
        }

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

        return user;
    }
}