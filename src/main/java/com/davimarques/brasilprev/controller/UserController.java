package com.davimarques.brasilprev.controller;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    UserServices userServices;

    @PostMapping
    public void createUser(@RequestBody User user) {
        UserDTO userDTO = userServices.create(user);
    }

}
