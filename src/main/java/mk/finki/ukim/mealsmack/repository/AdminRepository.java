package mk.finki.ukim.mealsmack.repository;

import mk.finki.ukim.mealsmack.Data.DataHolder;
import mk.finki.ukim.mealsmack.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminRepository {
    public Optional<Admin> findByUsernameAndPassword(String username, String password){
        return DataHolder.admins.stream()
                .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                .findFirst();
    }
}
