package com.scry.poc.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-08-09T11:03:31.628Z")

public class ApiException extends Exception {
	/**
	 * This field is used to define serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
