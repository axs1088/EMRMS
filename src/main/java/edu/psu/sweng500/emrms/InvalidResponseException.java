package edu.psu.sweng500.emrms;


/**
 * An invalid response was received from the server.
 */
public class InvalidResponseException extends Exception {
	private static final long serialVersionUID = -1558649247821123935L;
	
	public InvalidResponseException() {
		super();
	}
	
	public InvalidResponseException(String message) {
		super(message);
	}
	
	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
	}
}
