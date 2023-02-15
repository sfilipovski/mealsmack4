package mk.finki.ukim.mealsmack.service;

import mk.finki.ukim.mealsmack.model.Admin;

public interface AdminService {
    Admin login(String username, String password);
}
