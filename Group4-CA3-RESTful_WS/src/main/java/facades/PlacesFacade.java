package facades;

import entity.Place;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Places Facade.
 */
public class PlacesFacade
{
  private EntityManager em;

  public PlacesFacade(EntityManager em)
  {
    this.em = em;
  }

  /**
   * Gets all places
   * @return List of places.
   */
  public List<Place> getAllPlaces()
  {
    List<Place> _placeList = em.createQuery("Select c from PLACE c").getResultList();

    return _placeList;
  }

  /**
   * Creates a new place entity.
   * @return Place Entity.
   */
  public Place addPlace(Place place)
  {
      em.getTransaction().begin();
      em.persist(place);
      em.getTransaction().commit();

      return place;
  }
}