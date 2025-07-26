package com.desafioitau.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnProcessableEntity extends RuntimeException {
    public  UnProcessableEntity(String message){super(message);}
}
