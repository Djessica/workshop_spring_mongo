package com.eickstaedtdjessica.workshopmongo.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eickstaedtdjessica.workshopmongo.domain.User;

//informa que essa classe é um recurso rest
//requestmapping informa o endpoint

@RestController
@RequestMapping(value = "/users")
public class UsereResource {

	@RequestMapping(method = RequestMethod.GET)
	//responseentity retorna resposta http
	public ResponseEntity<List<User>> getAll(){
		User maria = new User("1","MAria","maria@gmail.com");
		User joao = new User("2","Joao","joao@gmail.com");
		
		List<User> lista = new ArrayList<User>();
		lista.addAll(Arrays.asList(maria,joao));
		
		//ok instancia o responseentity com o código de resposta http
		//o corpo da resposta vai trazer a lista
		return ResponseEntity.ok().body(lista);
	}
}
