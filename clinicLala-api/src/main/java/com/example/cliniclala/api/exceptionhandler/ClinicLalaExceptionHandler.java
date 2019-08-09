package com.example.cliniclala.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClinicLalaExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUser = this.messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDev = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		List erros = Arrays.asList(new Erro(mensagemUser, mensagemDev));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String mensagemUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUser, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class} )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		String mensagemUser = this.messageSource.getMessage("recurso.nao-permitido", null, LocaleContextHolder.getLocale());
		String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
		List erros = Arrays.asList(new Erro(mensagemUser, mensagemDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
		
	}

	public static class Erro {

		private String mensagemUser;
		private String mensagemDev;

		public Erro(String mensagemUser, String mensagemDev) {
			this.mensagemDev = mensagemDev;
			this.mensagemUser = mensagemUser;
		}

		public String getMensagemUser() {
			return mensagemUser;
		}

		public String getMensagemDev() {
			return mensagemDev;
		}
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros = criarListaDeErros(e.getBindingResult());
		return handleExceptionInternal(e, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
		String mensagemUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		String mensagemDev = fieldError.toString();
		erros.add(new Erro(mensagemUser, mensagemDev));
		}
		
		return erros;
	}
}
