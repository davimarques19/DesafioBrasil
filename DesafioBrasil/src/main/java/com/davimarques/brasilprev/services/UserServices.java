package com.davimarques.brasilprev.services;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.exceptions.ObjectNotFoundException;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.repository.UserRepository;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(UserDTO::create).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserDTO::create).orElseThrow(() ->
                new ObjectNotFoundException("Usuário não encontrado"));
    }

    public UserDTO create(User user) {
        Assert.isNull(user.getId(), "Não foi possível inserir o usuário");
        return UserDTO.create(userRepository.save(user));
    }

    public UserDTO editUser(User user, Long id) {
        Assert.notNull(id, "Não foi possível encontrar o id");

        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            User userDb = optional.get();
            userDb.setName(user.getName());
            userDb.setCpf(user.getCpf());
            userDb.setAddress(user.getAddress());

            userRepository.save(userDb);

            return UserDTO.create(userDb);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
