package com.eickstaedtdjessica.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eickstaedtdjessica.workshopmongo.domain.User;
import com.eickstaedtdjessica.workshopmongo.dto.UserDTO;
import com.eickstaedtdjessica.workshopmongo.repository.UserRepository;
import com.eickstaedtdjessica.workshopmongo.services.exception.ObjectNotFoundException;

//informa que é um servico que pode ser injetado em outras classes
@Service
public class UserService {

	// instancia automaticamente um objeto, ou seja, não precisa dar new
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repo.insert(user);

	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}
