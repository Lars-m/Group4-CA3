package facades;

import entity.Address;
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
public class AddressFacade 
{
    private EntityManager em;
    
  public AddressFacade(EntityManager em) 
  {
    this.em = em; 
  }

  /*
    Gets all addresses.
  */
  public List<Address> getAllAddresses() 
  {
    List<Address> addressList = em.createQuery("Select c from ADDRESS c").getResultList();

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