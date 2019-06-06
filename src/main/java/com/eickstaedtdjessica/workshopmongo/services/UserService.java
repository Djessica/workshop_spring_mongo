package com.eickstaedtdjessica.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eickstaedtdjessica.workshopmongo.domain.User;
import com.eickstaedtdjessica.workshopmongo.repository.UserRepository;
//informa que é um servico que pode ser injetado em outras classes
@Service
public class UserService {
	
	//instancia automaticamente um objeto, ou seja, não precisa dar new
	@Autowired
	private UserRepository repo;

	public List<User> findAll(){
		return repo.findAll();
	}
}
