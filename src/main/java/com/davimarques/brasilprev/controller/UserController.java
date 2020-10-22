package com.davimarques.brasilprev.controller;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping()
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(userServices.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity createUser(@RequestBody User user) {
        UserDTO c = userServices.create(user);

        URI location = getURI(c.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userServices.editUser(user, id) != null ?

                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity deleteCar(@PathVariable("id") Long id){
        userServices.delete(id);
        return ResponseEntity.ok().build();
    }
}
