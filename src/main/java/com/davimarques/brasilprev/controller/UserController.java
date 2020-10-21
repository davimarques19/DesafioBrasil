package com.davimarques.brasilprev.controller;

import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    UserServices userServices;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @GetMapping()
    public Iterable<User> getUsers() {
        return userServices.getUsers();
    }
}
