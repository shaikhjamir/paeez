package com.paeez.rest.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.paeez.core.model.UserBets;
import com.paeez.core.repositories.mongo.GenericBetRepository;
import com.paeez.core.services.api.GroupBetsService;

/**
 * Created by Shrikant on 1/9/15.
 */
@Component
public class UserBetsValidator implements Validator {

    @Autowired
    private GroupBetsService groupBetsService;
    
    @Autowired
	protected GenericBetRepository genericBetRepository;
    
    @Autowired
    public UserBetsValidator(GenericBetRepository genericBetRepository) {
        this.genericBetRepository = genericBetRepository;
    }
    
    public boolean supports(Class clazz) {
        return UserBets.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "userId", "userId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "groupId", "groupId.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "genericBetId", "genericBetId.null");
        
        UserBets userBet = (UserBets) obj;
        if (userBet.getGenericBetId() != null && genericBetRepository.findOne(userBet.getGenericBetId()) == null) {
          
        	e.rejectValue("genericBetId", "genericBetId does not exist");
        }

     }
}
