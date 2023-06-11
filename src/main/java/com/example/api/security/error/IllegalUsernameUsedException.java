package com.example.api.security.error;

public class IllegalUsernameUsedException extends RuntimeException {
    public IllegalUsernameUsedException(String s) {
        super(s);
    }
}
