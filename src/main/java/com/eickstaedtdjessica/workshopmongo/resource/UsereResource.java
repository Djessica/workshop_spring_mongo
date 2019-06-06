package com.eickstaedtdjessica.workshopmongo.resource;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eickstaedtdjessica.workshopmongo.domain.User;
import com.eickstaedtdjessica.workshopmongo.dto.UserDTO;
import com.eickstaedtdjessica.workshopmongo.services.UserService;

//informa que essa classe é um recurso rest
//requestmapping informa o endpoint

@RestController
@RequestMapping(value = "/users")
public class UsereResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	//responseentity retorna resposta http
	public ResponseEntity<List<UserDTO>> getAll(){
		
		List<User> lista = service.findAll();
		List<UserDTO> listDto = lista.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		//ok instancia o responseentity com o código de resposta http
		//o corpo da resposta vai trazer a lista
		return ResponseEntity.ok().body(listDto);
	}
}
