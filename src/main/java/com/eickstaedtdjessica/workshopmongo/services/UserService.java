package com.eickstaedtdjessica.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eickstaedtdjessica.workshopmongo.domain.User;
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
}
