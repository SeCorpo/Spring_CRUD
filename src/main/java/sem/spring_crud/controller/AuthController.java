package sem.spring_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sem.spring_crud.service.AuthService;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:36507")
//@Controller
//public class AuthController {
//    private final AuthService authService;
//
//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "redirect:/login.html";
//    }
//
//    @PostMapping("/api/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model) {
//        if (authService.login(username, password)) {
//            return "redirect:/welcome.html";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "redirect:/login.html";
//        }
//    }
//
//    @GetMapping("/register")
//    public String registerPage() {
//        return "redirect:/register.html";
//    }
//
//    @PostMapping("/api/register")
//    public String register(@RequestParam String username, @RequestParam String password, Model model) {
//        if (authService.register(username, password)) {
//            return "redirect:/login.html";
//        } else {
//            model.addAttribute("error", "Username already exists");
//            return "redirect:/register.html";
//        }
//    }
//}

@CrossOrigin(origins = "*") // allow all because flutter dev keeps changing ports
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        if (authService.login(username, password)) {
            response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
        }
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        if (authService.register(username, password)) {
            response.put("success", true);
            response.put("message", "Registration successful");
        } else {
            response.put("success", false);
            response.put("message", "Username already exists");
        }
        return response;
    }
}