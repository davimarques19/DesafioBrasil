package com.davimarques.brasilprev.services;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public UserDTO create(User user) {

        Assert.isNull(user.getId(), "Não foi possível inserir o carro");
        return UserDTO.create(userRepository.save(user));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }

}
