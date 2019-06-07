package com.eickstaedtdjessica.workshopmongo.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eickstaedtdjessica.workshopmongo.services.exception.ObjectNotFoundException;

//indica que essa classe vai tratar possiveis erros
@ControllerAdvice
public class ResourceExceptionHandler {

	//quando encontrar a exceção ObjectNotFoundException dever ser feito o que está dentro do método abaixo
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError erro = new StandardError(System.currentTimeMillis(), status.value(),"Não encontrado" , e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}

}
