package mk.finki.ukim.mealsmack.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mealsmack.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getRestaurantPage(Model model){
        model.addAttribute("restaurants", restaurantService.findAll());
        model.addAttribute("cuisines", restaurantService.getCuisines());
        model.addAttribute("bodyContent", "restaurants");
        return "master-template";
    }


    @GetMapping("/map/{id}")
    public String getMapping(@PathVariable Long id, Model model){
        if(restaurantService.findById(id).isPresent()){
            model.addAttribute("restaurant",restaurantService.findById(id).get());
            model.addAttribute("bodyContent", "map");
            return "master-template";
        }
        return "redirect:/home";
    }

    @PostMapping
    public String filterCategory(Model model, HttpServletRequest request){
        String municipality = request.getParameter("municipality");
        String amenity = request.getParameter("amenity");
        String cuisine = request.getParameter("cuisine");

        request.setAttribute("municipality",municipality);
        request.setAttribute("amenity",amenity);
        request.setAttribute("cuisine",cuisine);

        model.addAttribute("restaurants", restaurantService.filterRestaurants(municipality, amenity, cuisine));
        model.addAttribute("cuisines", restaurantService.getCuisines());

        return "restaurants";
    }

    @GetMapping(path = "/add")
    public String getAddRestaurantPage(Model model){
        model.addAttribute("cuisines",restaurantService.getCuisines());
        model.addAttribute("bodyContent","add-restaurant");
        return "master-template";
    }

    @PostMapping(path = "/add")
    public String addRestaurant(@RequestParam String name, @RequestParam String municipality , @RequestParam String latitude, @RequestParam String longitude, @RequestParam String amenity, @RequestParam String cuisine){
        List<String> cuisines = new ArrayList<>();
        cuisines.add(cuisine);
        restaurantService.save(name, municipality, longitude, latitude, cuisines, amenity);

        return "redirect:/restaurants";
    }

    @DeleteMapping( "/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id){
        restaurantService.delete(id);
        return "redirect:/home";
    }
}
