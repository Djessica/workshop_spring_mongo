package com.eickstaedtdjessica.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eickstaedtdjessica.workshopmongo.domain.Post;
import com.eickstaedtdjessica.workshopmongo.domain.User;
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
		
		
		Post post1 = new Post(null, sdf.parse("10/06/2019"),"teste", "testeeeeeee",maria);
		Post post2 = new Post(null, sdf.parse("10/06/2019"),"teste2", "testeeeeeee2",alex);
		
		userRepo.saveAll(Arrays.asList(maria,alex,bob));
		postRepo.saveAll(Arrays.asList(post1,post2));
	}

}
