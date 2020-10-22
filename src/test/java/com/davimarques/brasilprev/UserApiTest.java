package com.davimarques.brasilprev;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.model.Address;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BrasilprevApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiTest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private UserServices userServices;

    private ResponseEntity<UserDTO> getUser(String url) {
        return
                rest.getForEntity(url, UserDTO.class);
    }

    private ResponseEntity<List<UserDTO>> findAll(String url) {
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {
                });
    }


    @Test
    public void testSaveUser() {

        User user = new User();
        Address address = new Address();
        user.setName("Primeiro Teste");
        user.setCpf("11122233344");
        address.setCep("05555-110");
        address.setCity("São Paulo");
        address.setStreet("Rua nova");
        address.setNumberHouse(123);

        // Insert
        ResponseEntity response = rest.postForEntity("/api/user/v1", user, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        UserDTO userDTO = getUser(location).getBody();

        assertNotNull(userDTO);
        assertEquals("Primeiro Teste",userDTO.getName());

        // Deletar o objeto
        rest.delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getUser(location).getStatusCode());
    }

    @Test
    public void testGetStatusOk() {

        ResponseEntity<UserDTO> response = getUser("/api/user/v1/1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        UserDTO userDTO = response.getBody();
        assertEquals("Primeiro Teste", userDTO.getName());
    }

    @Test
    public void testGetStatusNotFound() {

        ResponseEntity response = getUser("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
