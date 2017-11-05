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
   * @param city City
   * @param street Street
   * @param zip Zip Code.
   * @param imageUrl Image Url.
   * @param description Place Description
   * @return Place Entity.
   */
  public Place addPlace(String city, String street, int zip, String imageUrl, String description, int rating)
  {
    em.getTransaction().begin();

    Place _place = new Place();

    _place.setCity(city);
    _place.setStreet(street);
    _place.setZip(zip);
    _place.setImageUrl(imageUrl);
    _place.setDescription(description);
    _place.setPlaceRating(rating);

    em.persist(_place);
    em.getTransaction().commit();

    return _place;
  }
}