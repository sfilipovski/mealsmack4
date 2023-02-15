package mk.finki.ukim.mealsmack.model;

import lombok.Data;

import java.util.List;

@Data
public class Restaurant {
    Long id;
    static Long idVal=1L;
    String name;
    String municipality;
    String lon;
    String lat;
    List<String> cuisine;
    String amenity;

    public Restaurant( String lon, String lat, String municipality, String name, List<String> cuisine, String amenity) {
        this.id = idVal++;
        this.name = name;
        this.municipality = municipality;
        this.lon = lon;
        this.lat = lat;
        this.cuisine = cuisine;
        this.amenity = amenity;
    }
}
