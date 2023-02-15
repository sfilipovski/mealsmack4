package mk.finki.ukim.mealsmack.service.impl;

import mk.finki.ukim.mealsmack.model.Restaurant;
import mk.finki.ukim.mealsmack.repository.RestaurantRepository;
import mk.finki.ukim.mealsmack.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void save(String name, String municipality, String lon, String lat, List<String> cuisine, String amenity) {
        restaurantRepository.save(name, municipality, lon, lat, cuisine, amenity);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> filterRestaurants(String name, String amenity, String cuisine) {
        return restaurantRepository.filterRestaurants(name, amenity, cuisine);
    }

    @Override
    public Set<String> getCuisines() {
        return restaurantRepository.getCuisines();
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.delete(id);
    }
}
