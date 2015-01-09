package com.paeez.core.services.util;

import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Shrikant on 1/4/15.
 */
public class InputValidations {

    @Autowired
    private static GroupRepository groupRepository;
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static BetsCartRepository betsCartRepository;
    @Autowired
    private static GroupUsersRepository groupUsersRepository;
    @Autowired
    private static UserBetsRepository userBetsRepository;

    public static void validateInputIdForNull(String errorMessage, String ... Ids) {
        for (String id : Ids){
            if (id == null || id.isEmpty())
                throw new InputIdNullOrEmptyException(errorMessage);
        }
    }

    public static void validateGroupExists(String groupId, String errorMessage) {
        if (groupRepository.findOne(groupId) == null)
            throw new GroupDoesNotExistsException(errorMessage);
    }

    public static void validateGroupAlreadyExists(String groupId, String errorMessage) {
        if (groupRepository.findOne(groupId) != null)
            throw new GroupDoesNotExistsException(errorMessage);
    }

    public static void validateUserExists(String userId) {

    }

    public static void validateUserAlreadyExists(String userId) {

    }

    public static void validateBetsCartExists(String betsCartId, String errorMessage) {
        if (betsCartRepository.findOne(betsCartId) == null)
            throw new BetsCartDoesNotExistsException(errorMessage);
    }


    public static void validateForNull(Object object, String errorMessage){
        if (object == null)
            throw new NullArgumentException(errorMessage);
    }
}
