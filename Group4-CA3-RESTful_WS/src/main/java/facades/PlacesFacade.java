package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 */
public class PlacesFacade 
{
    private EntityManager em;
    
  public PlacesFacade(EntityManager em) 
  {
    this.em = em; 
  }

  /**
    Gets all Places.
  */
  public List<Place> getAllPlaces() 
  {
    List<Place> placeList = em.createQuery("Select c from PLACE c").getResultList();

    return placeList;
  }
  
  /**
    Adds new place entry.
  */
  public Place addPlace(Place place)
  {
    em.getTransaction().begin();
    em.persist(place);
    em.getTransaction().commit();
    
    return place;
  }
}