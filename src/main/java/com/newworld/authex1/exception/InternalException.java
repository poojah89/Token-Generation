package com.newworld.authex1.exception;



import lombok.Data;
@Data
public class InternalException extends Exception {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -508006614100526194L;
	
	public final int outcomeCode;
	public final String outcomeMessage;
	public final String internalMessage;

}