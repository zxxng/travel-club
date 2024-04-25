package io.nextree.travelclub.web.util.exception;

public class InvalidMemberRoleException extends RuntimeException {
    private static final long serialVersionUID = -8812955226330753783L;
    public InvalidMemberRoleException(String message) {
        super(message);
    }
}

