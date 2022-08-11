package com.scry.poc.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-08-09T11:03:31.628Z")

public class NotFoundException extends ApiException {
	/**
	 * This field is used to define serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private int code;

	public NotFoundException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
