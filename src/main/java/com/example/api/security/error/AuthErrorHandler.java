package com.example.api.security.error;

import com.example.api.security.dto.AuthErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthErrorHandler {
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthErrorResponse handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException invalidJwtAuthenticationException){
        return new AuthErrorResponse(invalidJwtAuthenticationException.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public AuthErrorResponse handleUserAlreadyExistException(UserAlreadyExistsException userAlreadyExistsException){
        return new AuthErrorResponse(userAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(IllegalUsernameUsedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthErrorResponse handleIllegalUsernameUsedException(IllegalUsernameUsedException illegalUsernameUsedException){
        return new AuthErrorResponse(illegalUsernameUsedException.getMessage());
    }
}
