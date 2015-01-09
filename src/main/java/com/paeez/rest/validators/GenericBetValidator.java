package com.paeez.rest.validators;

import com.paeez.core.model.GenericBet;
import com.paeez.rest.util.Utilities;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Shrikant on 1/9/15.
 */
public class GenericBetValidator implements Validator {
    public boolean supports(Class clazz) {
        return GenericBet.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "options", "options.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "createdBy", "createdBy.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "closingTime", "closingTime.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "betType", "betType.null");

        GenericBet bet = (GenericBet) obj;

        if (bet.getOptions() != null && bet.getOptions().size() < 2)
            e.rejectValue("BetOptions", "Bet options should at least be two");

        //if (Utilities.stringToDate())
    }
}
