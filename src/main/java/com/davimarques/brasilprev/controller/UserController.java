package com.davimarques.brasilprev.controller;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            UserDTO c = userServices.create(user);

            URI location = getURI(c.getId());
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userServices.editUser(user, id) != null ?

                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity getAll() {

        return ResponseEntity.ok(userServices.findAll());
    }

    private URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
