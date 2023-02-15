package mk.finki.ukim.mealsmack.model;

import lombok.Data;

@Data
public class Admin {
    String username;
    String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
