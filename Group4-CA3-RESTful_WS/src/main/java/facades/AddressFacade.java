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

/*
    Address Facade to get and set Address entity.
*/
public class AddressFacade implements IAddressFacade 
{

  EntityManagerFactory emf;

  public AddressFacade(EntityManagerFactory emf) 
  {
    this.emf = emf;   
  }

  private EntityManager getEntityManager() 
  {
    return emf.createEntityManager();
  }
 
  /*
    Gets all addresses.
  */
  public List<Address> getAllAddresses() 
  {
    List<Address> addressList = getEntityManager().createQuery("SELECT * FROM Address").getResultList();
      
    return addressList;
  }
  
  /*
    Adds new address entry.
  */
  public Address addAddress(Address address)
  {
    em.getTransaction().begin();
    em.persist(address);
    em.getTransaction().commit();
    
    return address;
  }
}