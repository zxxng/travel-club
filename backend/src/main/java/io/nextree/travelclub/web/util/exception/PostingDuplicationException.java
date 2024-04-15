package io.nextree.travelclub.web.util.exception;

public class PostingDuplicationException extends RuntimeException{
	private static final long serialVersionUID = -7196327736293090551L;
	
	public PostingDuplicationException(String message) {
		super(message);
	}
}
