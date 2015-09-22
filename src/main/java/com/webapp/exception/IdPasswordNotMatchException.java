package com.webapp.exception;

// 자바는 아무객체나 익셉션 객체가 될 수 없다. 그래서 익셉션 클래스를 상속받아야 됨
public class IdPasswordNotMatchException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	// 객체간의 버전관리를 위해 필요, 크게 의미는 없음

	public IdPasswordNotMatchException() {
		super();
	}
	
	public IdPasswordNotMatchException(String message) {
		super(message);
	}
	
	public IdPasswordNotMatchException(Throwable cause) {
		super(cause);
	}
	
	public IdPasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
