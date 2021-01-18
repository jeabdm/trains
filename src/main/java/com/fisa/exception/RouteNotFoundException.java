package com.fisa.exception;

public class RouteNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public RouteNotFoundException(String message) {
		super(message);
	}
}
