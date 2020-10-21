package com.davimarques.brasilprev.services;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.repository.UserRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public UserDTO create(User user) {
        Assert.isNull(user.getId(), "Falha ao adicionar usuário");
        return UserDTO.create(userRepository.save(user));
    }
}
