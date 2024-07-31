package com.luisfelipe.todosimple.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataBindingViolationException extends DataIntegrityViolationException {

    public DataBindingViolationException(String msg) {
        super(msg);
    }

}
