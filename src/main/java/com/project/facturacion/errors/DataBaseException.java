package com.project.facturacion.errors;

public class DataBaseException extends RuntimeException{
	
	private static final long serialVersionUID = 117327896358965477L;

	public DataBaseException() {
		super("Contacte al administrador de base datos, hubo un error al guardar el registro");
	}

}
