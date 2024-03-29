package com.eickstaedtdjessica.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eickstaedtdjessica.workshopmongo.domain.Post;
import com.eickstaedtdjessica.workshopmongo.domain.User;
import com.eickstaedtdjessica.workshopmongo.dto.AuthorDTO;
import com.eickstaedtdjessica.workshopmongo.dto.CommentDTO;
import com.eickstaedtdjessica.workshopmongo.repository.PostRepository;
import com.eickstaedtdjessica.workshopmongo.repository.UserRepository;
//deixa claro para o spring que é uma configuração
@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//salva primeiro para que o usuario tenha um id proprio
		userRepo.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("10/06/2019"),"teste", "testeeeeeee",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("10/06/2019"),"teste2", "testeeeeeee2",new AuthorDTO(alex));

		
		CommentDTO com1 = new CommentDTO("Boa viagem mano!",sdf.parse("11/06/2019"), new AuthorDTO(alex));
		CommentDTO com2 = new CommentDTO("Aproveite!",sdf.parse("22/03/2018"),new AuthorDTO(bob));
		CommentDTO com3 = new CommentDTO("Tenha um ótimo dia!",sdf.parse("23/03/2018"),new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(com1,com2));
		post2.getComments().addAll(Arrays.asList(com3));
		
		postRepo.saveAll(Arrays.asList(post1,post2));
		
		maria.getPost().addAll(Arrays.asList(post1,post2));
		userRepo.save(maria);
	}

}
