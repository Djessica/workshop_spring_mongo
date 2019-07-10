package com.eickstaedtdjessica.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.eickstaedtdjessica.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	/*o @query permite fazer uma consulta personalizada no mongo, 
	 * portanto o nome pode ser qualquer um
	 * nessa consulta vai ser passado um json
	 * o ?0 vai dizer que é para pegar o primeiro parametro que vier no método, no caso o text
	 * a letra i diz que é para ignorar leters maiusculas ou minusculas*/
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);

	/*o método tem esse nome porque ele usa critérios prontos do mongodb, nesse caso ele vai usar o
	findBy do mongo
	nome do que eu quero pesquisar
	containing do mongo
	ignoreCase do mongo*/
	List<Post> findByTitleContainingIgnoreCase(String title);
	
	/* Buscar posts contendo um dado string em qualquer lugar 
	 * (no título, corpo ou comentários) e em um dado intervalo de datas*/
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2}},"
			+ "  { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, "
						+ "{  'body': { $regex: ?0, $options: 'i' }},"
						+ "{  'comments.text': { $regex: ?0, $options: 'i' } } ]  } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
