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
    @ExceptionHandler(BetsCartDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors betsCartDoesNotExistsExceptionHandler(BetsCartDoesNotExistsException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(GenericBetDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors genericBetDoesNotExistExceptionHandler(GenericBetDoesNotExistException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(GroupAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors groupAlreadyExistsExceptionHandler(GroupAlreadyExistsException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(GroupDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors groupDoesNotExistsExceptionHandler(GroupDoesNotExistsException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
        return new VndErrors("error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors userDoesNotExistsExceptionHandler(UserDoesNotExistsException ex) {
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
