package com.paeez.rest.exceptions.advices;

import com.paeez.core.services.exceptions.*;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Shrikant on 1/5/15.
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ResponseBody
    @ExceptionHandler(GroupBetsDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors betsCartDoesNotExistsExceptionHandler(GroupBetsDoesNotExistsException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(GenericBetDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors genericBetDoesNotExistExceptionHandler(GenericBetDoesNotExistException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NullArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    VndErrors nullArgumentExceptionHandler(NullArgumentException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InputIdNullOrEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    VndErrors inputIdNullOrEmptyExceptionHandler(InputIdNullOrEmptyException ex) {
        return new VndErrors("error", ex.getMessage());
    }


}
