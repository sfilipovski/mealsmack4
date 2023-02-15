package mk.finki.ukim.mealsmack.repository;

import mk.finki.ukim.mealsmack.Data.DataHolder;
import mk.finki.ukim.mealsmack.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RestaurantRepository {

    public void save(String name, String municipality, String lon, String lat, List<String> cuisine, String amenity){
        DataHolder.restaurants.add(new Restaurant(lon, lat, municipality, name,cuisine, amenity));
    }

    public List<Restaurant> findAll(){
        return DataHolder.restaurants;
    }

    public Optional<Restaurant> findById(Long id){
        return DataHolder.restaurants.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Restaurant> filterRestaurants(String name, String amenity, String cuisine){
        List<Restaurant> restaurants = findAll();

        if(!name.equals("all") && !amenity.equals("all") && !cuisine.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getMunicipality().equals(name) && r.getAmenity().equals(amenity) && r.getCuisine().stream().anyMatch(i -> i.equals(cuisine)))
                    .collect(Collectors.toList());
        }
        else if(name.equals("all") && !amenity.equals("all") && !cuisine.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getAmenity().equals(amenity) && r.getCuisine().stream().anyMatch(i -> i.equals(cuisine)))
                    .collect(Collectors.toList());
        }
        else if(!name.equals("all") && amenity.equals("all") && !cuisine.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getMunicipality().equals(name) && r.getCuisine().stream().anyMatch(i -> i.equals(cuisine)))
                    .collect(Collectors.toList());
        }
        else if(!name.equals("all") && !amenity.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getMunicipality().equals(name) && r.getAmenity().equals(amenity))
                    .collect(Collectors.toList());
        }
        else if(name.equals("all") && amenity.equals("all") && !cuisine.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getCuisine().stream().anyMatch(i -> i.equals(cuisine)))
                    .collect(Collectors.toList());
        }
        else if(name.equals("all") && !amenity.equals("all")){
            return restaurants.stream()
                    .filter(r ->  r.getAmenity().equals(amenity))
                    .collect(Collectors.toList());
        }
        else if(!name.equals("all")){
            return restaurants.stream()
                    .filter(r -> r.getMunicipality().equals(name))
                    .collect(Collectors.toList());
        }
        return restaurants;
    }

    public Set<String> getCuisines(){
        List<Restaurant> restaurants = findAll();
        Set<String> cuisines = new HashSet<>();

        for(Restaurant restaurant: restaurants){
            for(String s: restaurant.getCuisine()){
                if(s!=null && !s.isEmpty())
                    cuisines.add(s);
            }
        }
        return cuisines;
    }

    public void delete(Long id){
        DataHolder.restaurants.removeIf(r -> r.getId().equals(id));
    }

}