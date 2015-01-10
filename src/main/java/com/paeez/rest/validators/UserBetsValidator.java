package com.paeez.rest.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.paeez.core.model.UserBets;
import com.paeez.core.services.api.GenericBetService;

/**
 * Created by Shrikant on 1/9/15.
 */
@Component
public class UserBetsValidator implements Validator {

    
    @Autowired
	protected GenericBetService genericBetService;
    
    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
    	// It is getting called for other methods of the Controller as well, which it should not, need to find a workaround
    	// return UserBets.class.equals(clazz);
    	return true ;
    }

    
    public void validate(Object obj, Errors e) {
    	
    	if (obj == null || ( (obj instanceof UserBets) == false) )
    		return ;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "userId", "userId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupId", "groupId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "genericBetId", "genericBetId.null");
        
        UserBets userBet = (UserBets) obj;
        if (userBet.getGenericBetId() != null && genericBetService.findById(userBet.getGenericBetId()) == null) {
          
        	e.rejectValue("genericBetId", "genericBetId does not exist");
        }
		
     }
}
