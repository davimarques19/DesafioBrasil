package com.davimarques.brasilprev;

import com.davimarques.brasilprev.DTO.UserDTO;
import com.davimarques.brasilprev.exceptions.ObjectNotFoundException;
import com.davimarques.brasilprev.model.Address;
import com.davimarques.brasilprev.model.User;
import com.davimarques.brasilprev.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserServices userServices;

	@Test
	void testCreateUser() {

		User user = new User();
		Address address = new Address();
		user.setName("Teste Criar 1");
		user.setCpf("11122233344");
		address.setCep("05555-110");
		address.setCity("São Paulo");
		address.setStreet("Rua nova");
		address.setNumberHouse(123);
		user.setAddress(address);

		UserDTO userDTO = userServices.create(user);

		Long id = userDTO.getId();
		assertNotNull(id);

		// Deletar o objeto
		userServices.delete(id);

		// Verificar se deletou
		try {
			userServices.getUserById(id);
			fail("O usuário não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}

	@Test
	void testFindById() {

		User user = new User();
		Address address = new Address();
		user.setName("Teste FindById 1");
		user.setCpf("11122233344");
		address.setCep("05555-110");
		address.setCity("São Paulo");
		address.setStreet("Rua nova");
		address.setNumberHouse(123);
		user.setAddress(address);

		UserDTO userDTO = userServices.create(user);

		Long id = userDTO.getId();
		assertNotNull(id);
		// Buscar o objeto
		userDTO = userServices.getUserById(id);
		assertNotNull(userDTO);

		assertEquals("Teste FindById 1",userDTO.getName());

		// Deletar o objeto
		userServices.delete(id);

		// Verificar se deletou
		try {
			userServices.getUserById(id);
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}

	}

	@Test
	void testDeleteUser() {

		User user = new User();
		Address address = new Address();
		user.setName("Teste Delete 1");
		user.setCpf("11122233344");
		address.setCep("05555-110");
		address.setCity("São Paulo");
		address.setStreet("Rua nova");
		address.setNumberHouse(123);
		user.setAddress(address);

		UserDTO userDTO = userServices.create(user);

		Long id = userDTO.getId();
		assertNotNull(id);
		// Buscar o objeto
		userDTO = userServices.getUserById(id);
		assertNotNull(userDTO);

		assertEquals("Teste Delete 1",userDTO.getName());

		// Deletar o objeto
		userServices.delete(id);

		// Verificar se deletou
		try {
			userServices.getUserById(id);
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}

	}

	@Test
	public void testFindAll() {

		List<UserDTO> users = userServices.findAll(PageRequest.of(0, 10));

		assertEquals(3, users.size());
	}

}
