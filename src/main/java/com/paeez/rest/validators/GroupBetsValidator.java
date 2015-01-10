package com.paeez.rest.validators;

import com.paeez.core.model.GroupBets;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Shrikant on 1/9/15.
 */
@Component
public class GroupBetsValidator implements Validator {

    //@Autowired
    //private GroupSer
    public boolean supports(Class clazz) {
        return GroupBets.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "genericBetIds", "genericBetIds.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupId", "groupId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "importedByUserEmailAddress", "importedByUserEmailAddress.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "betType", "betType.null");

        GroupBets groupBets = (GroupBets) obj;

        if (groupBets.getGenericBetIds() != null && groupBets.getGenericBetIds().size() < 1)
            e.rejectValue("genericBetIds", "Bet genericBetIds should at least be 1");

        //TODO: check if groupId exists
        //if (betsCart.getGroupId() != null && betsCart)

        //TODO check if importedByUserEmailAddress exists
        //if (betsCart.getImportedByUserEmailAddress() != null && )

    }
}
