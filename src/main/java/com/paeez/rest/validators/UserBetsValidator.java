package com.paeez.rest.validators;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.GroupBetsService;
import com.paeez.core.services.api.GenericBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Shrikant on 1/9/15.
 */
@Component
public class UserBetsValidator implements Validator {

    @Autowired
    private GroupBetsService groupBetsService;
    @Autowired
    private GenericBetService genericBetService;

    public boolean supports(Class clazz) {
        return UserBets.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "userId", "userId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupId", "groupId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "choice", "choice.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "genericBetId", "genericBetId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "betsCartId", "betsCartId.null");

        UserBets userBet = (UserBets) obj;

        if (userBet.getGroupBetsId() != null && groupBetsService.findById(userBet.getGroupBetsId()) != null)
            e.rejectValue("betsCartId", "betsCartId does not exist");

        if (userBet.getGenericBetId() != null && genericBetService.findById(userBet.getGenericBetId()) != null)
            e.rejectValue("genericBetId", "genericBetId does not exist");

        //TODO check if choice is amongst the correct one from genericBet

        //TODO check if group exists

        //TODO check if user exists
    }
}
