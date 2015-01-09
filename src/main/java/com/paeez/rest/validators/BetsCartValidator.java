package com.paeez.rest.validators;

import com.paeez.core.model.BetsCart;
import com.paeez.core.model.GenericBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Shrikant on 1/9/15.
 */
@Component
public class BetsCartValidator implements Validator {

    //@Autowired
    //private GroupSer
    public boolean supports(Class clazz) {
        return BetsCart.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "genericBetIds", "genericBetIds.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupId", "groupId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "importedByUserEmailAddress", "importedByUserEmailAddress.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "betType", "betType.null");

        BetsCart betsCart = (BetsCart) obj;

        if (betsCart.getGenericBetIds() != null && betsCart.getGenericBetIds().size() < 1)
            e.rejectValue("genericBetIds", "Bet genericBetIds should at least be 1");

        //TODO: check if groupId exists
        //if (betsCart.getGroupId() != null && betsCart)

        //TODO check if importedByUserEmailAddress exists
        //if (betsCart.getImportedByUserEmailAddress() != null && )

    }
}
