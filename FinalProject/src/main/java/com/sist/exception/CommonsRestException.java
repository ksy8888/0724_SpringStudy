package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsRestException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("===== RuntumeException 발생 ======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	//IOException , SqlException , Exception
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("===== IOException 발생 ======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("===== SQLException 발생 ======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("===== Exception 발생 ======");
		ex.printStackTrace();
		System.out.println("=================================");
	}
}
