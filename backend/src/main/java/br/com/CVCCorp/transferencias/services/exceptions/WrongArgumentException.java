package br.com.CVCCorp.transferencias.services.exceptions;

public class WrongArgumentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public WrongArgumentException(String msg) {
		super(msg);
	}

}
