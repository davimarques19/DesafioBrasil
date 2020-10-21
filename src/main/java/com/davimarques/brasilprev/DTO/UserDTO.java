/*
package com.davimarques.brasilprev.DTO;

import com.davimarques.brasilprev.model.Address;
import com.davimarques.brasilprev.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String cpf;
    private Address address;

    public static UserDTO create(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }
}
*/
