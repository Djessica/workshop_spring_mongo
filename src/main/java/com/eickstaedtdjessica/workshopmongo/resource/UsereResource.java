package com.eickstaedtdjessica.workshopmongo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	//o caminho para esse método vai ser users/numero do id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//o @PathVariable verifica se o id que está no método está igual ao que foi passado na url
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User obj = service.findById(id);
		
		//ok instancia o responseentity com o código de resposta http
		//o corpo da resposta vai trazer a lista
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

		@RequestMapping( method = RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
			
			User obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
}
