package seminar10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seminar10.entity.Session;
import seminar10.entity.User;
import seminar10.service.AuthService;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public Session loginUser(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @GetMapping("/logout/{userId}")
    public String logoutUser(@PathVariable Long userId) {
        return authService.logout(userId);
    }


    @PostMapping("/register")
    public User registerUser(@RequestParam String username, String email, String password){
        User user = new User(0L, username, email, password);
        return authService.register(user);
    }

    @GetMapping("/sessions")
    public List<Session> getAllSessions() {
        return authService.getAllSessions();
    }

}
