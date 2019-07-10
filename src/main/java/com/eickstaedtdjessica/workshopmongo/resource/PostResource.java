package com.eickstaedtdjessica.workshopmongo.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eickstaedtdjessica.workshopmongo.domain.Post;
import com.eickstaedtdjessica.workshopmongo.resource.utils.URL;
import com.eickstaedtdjessica.workshopmongo.services.PostService;

//informa que essa classe é um recurso rest
//requestmapping informa o endpoint

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	
	// o caminho para esse método vai ser users/numero do id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// o @PathVariable verifica se o id que está no método está igual ao que foi
	// passado na url
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post obj = service.findById(id);

		// ok instancia o responseentity com o código de resposta http
		// o corpo da resposta vai trazer a lista
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	//@RequestParam vai esperar um parametro na url, passando value=text ele vai identicar o parametro na url
	//se o parametro não for informado ele vai ser posto uma string vazia
	public ResponseEntity<List<Post>> findByIdTitle(@RequestParam(value = "text", defaultValue = "") String text) {

		text = URL.urlDecode(text);
		
		List<Post> lista = service.findByTitle(text);
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		text = URL.urlDecode(text);
		
		//o new Date(0L) cria uma data default do java
		Date min = URL.convertDate(minDate, new Date(0L));
		// new date gera uma data com o instante atual do sistema
		Date mx = URL.convertDate(maxDate, new Date());
		
		List<Post> lista = service.fullSearch(text, min, mx);
		return ResponseEntity.ok().body(lista);
	}

	
}
