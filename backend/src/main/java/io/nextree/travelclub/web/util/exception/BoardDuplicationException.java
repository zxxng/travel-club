package io.nextree.travelclub.web.util.exception;

public class BoardDuplicationException extends RuntimeException {
	private static final long serialVersionUID = -7196327736293090551L;

	public BoardDuplicationException(String message) {
		super(message);
	}
}
