package com.euro24.user;

import com.euro24.MatchResult.MultipleMatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/processMatches")
    public void processMatches(@RequestBody MultipleMatchResult multipleMatchResult) {
        userService.processMatches(multipleMatchResult);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
