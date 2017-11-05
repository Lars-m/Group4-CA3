/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  Place Entity class.
 */
@Entity(name = "PLACE")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final long serialVersionUID = 1L;

    private String zip;

    private String city;

    private String street;

    private String description;

    private String imageUrl;

    private String rating;

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getPlaceRating() { return rating; }

    public void setPlaceRating(String rating) { this.rating = rating; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCity() { return city; }

    public void setCity (String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getZip() { return zip; }

    public void setZip(String zip) { this.zip = zip; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Place))
        {
            return false;
        }
        Place other = (Place) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Place{" + "id=" + id +
                ", zip=" + zip +
                ", city" + city +
                ", street" + street +
                ", description=" + description +
                ", imageUrl=" + imageUrl +
                ", rating=" + rating +
                '}';
    }

}
