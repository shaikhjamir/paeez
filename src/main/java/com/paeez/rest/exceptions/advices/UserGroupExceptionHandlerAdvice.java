package com.paeez.rest.exceptions.advices;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paeez.core.services.exceptions.GroupAlreadyExistsException;
import com.paeez.core.services.exceptions.GroupDoesNotExistsException;
import com.paeez.core.services.exceptions.UserAlreadyExistsException;
import com.paeez.core.services.exceptions.UserDoesNotExistsException;
import com.paeez.core.services.exceptions.UserNotInGroupException;


@ControllerAdvice
public class UserGroupExceptionHandlerAdvice {

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
    @ExceptionHandler(UserNotInGroupException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors userNotInGroupExceptionHandler(UserNotInGroupException ex) {
        return new VndErrors("error", ex.getMessage());
    }
}
