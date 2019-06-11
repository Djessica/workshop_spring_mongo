package com.eickstaedtdjessica.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eickstaedtdjessica.workshopmongo.domain.Post;
import com.eickstaedtdjessica.workshopmongo.repository.PostRepository;
import com.eickstaedtdjessica.workshopmongo.services.exception.ObjectNotFoundException;

//informa que é um servico que pode ser injetado em outras classes
@Service
public class PostService {

	// instancia automaticamente um objeto, ou seja, não precisa dar new
	@Autowired
	private PostRepository repo;



	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	
}
