package mk.finki.ukim.mealsmack.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mealsmack.model.Admin;
import mk.finki.ukim.mealsmack.model.exception.InvalidCredentialsException;
import mk.finki.ukim.mealsmack.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private  final AdminService adminService;

    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String tryLogin(Model model , HttpServletRequest request){
        Admin admin = null;
        try{
            admin = adminService.login(request.getParameter("username"),request.getParameter("password"));
            request.getSession().setAttribute("admin", admin);
            return "redirect:/home";
        }
        catch(InvalidCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

}
