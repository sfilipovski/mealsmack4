package mk.finki.ukim.mealsmack.service.impl;

import mk.finki.ukim.mealsmack.model.Admin;
import mk.finki.ukim.mealsmack.model.exception.InvalidArgumentsException;
import mk.finki.ukim.mealsmack.model.exception.InvalidCredentialsException;
import mk.finki.ukim.mealsmack.repository.AdminRepository;
import mk.finki.ukim.mealsmack.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()) throw new InvalidArgumentsException();
        return adminRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidCredentialsException::new);
    }
}
