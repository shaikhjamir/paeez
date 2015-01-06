package com.paeez.core.services.util;

import com.paeez.core.model.BetsCart;
import com.paeez.core.model.GenericBet;
import com.paeez.core.model.GroupBetImport;
import com.paeez.core.model.UserPlayedBets;
import com.paeez.core.repositories.mongo.*;
import com.paeez.core.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Shrikant on 1/4/15.
 */
public class InputValidations {

    @Autowired
    private static GroupRepository groupRepository;
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static MatchBetRepository matchBetRepository;
    @Autowired
    private static BetsCartRepository betsCartRepository;
    @Autowired
    private static GroupUsersRepository groupUsersRepository;
    @Autowired
    private static UserPlayedBetsRepository userPlayedBetsRepository;

    public static void validateInputIdForNull(String errorMessage, String ... Ids) {
        for (String id : Ids){
            if (id == null || id.isEmpty())
                throw new InputIdNullOrEmptyException(errorMessage);
        }
    }

    public static void validateGenericBetForNull(GenericBet genericBet, String errorMessage) {
        if (genericBet == null)
            throw new NullArgumentException(errorMessage);

    }

    public static void validateGroupExists(String groupId, String errorMessage) {
        if (groupRepository.findOne(groupId) == null)
            throw new GroupIdDoesNotExistsException(errorMessage);
    }

    public static void validateGroupAlreadyExists(String groupId, String errorMessage) {
        if (groupRepository.findOne(groupId) != null)
            throw new GroupIdDoesNotExistsException(errorMessage);
    }

    public static void validateUserExists(String userId) {

    }

    public static void validateUserAlreadyExists(String userId) {

    }

    public static void checkGenericBetFound(GenericBet genericBet,  String errorMessage) {
        if (genericBet == null)
            throw new GenericBetNotFoundException(errorMessage);

    }

    public static void checkGenericBetsFound(List<GenericBet> genericBets, String errorMessage) {
        if (genericBets == null)
            throw new GenericBetNotFoundException(errorMessage);

    }

    public static void checkGroupBetImportFound(GroupBetImport groupBetImport,  String errorMessage) {
        if (groupBetImport == null)
            throw new GroupBetImportFoundNotFoundException(errorMessage);

    }

    public static void checkGroupBetsImportFound(List<GroupBetImport> groupBetImports,  String errorMessage) {
        if (groupBetImports == null)
            throw new GroupBetImportFoundNotFoundException(errorMessage);

    }

    public static void validateBetsCartExists(String betsCartId, String errorMessage) {
        if (betsCartRepository.findOne(betsCartId) == null)
            throw new BetsCartIdDoesNotExistsException(errorMessage);
    }

    public static void validateBetsCartForNull(BetsCart betsCart, String errorMessage) {
        if (betsCart == null)
            throw new NullArgumentException(errorMessage);

    }
    public static void checkBetsCartFound(BetsCart betsCart,  String errorMessage) {
        if (betsCart == null)
            throw new BetsCartNotFoundException(errorMessage);

    }

    public static void checkBetsCartsFound(List<BetsCart> betsCarts,  String errorMessage) {
        if (betsCarts == null)
            throw new BetsCartNotFoundException(errorMessage);

    }

    public static void validateUserPlayedBetsForNull(UserPlayedBets userPlayedBets, String errorMessage) {
        if (userPlayedBets == null)
            throw new NullArgumentException(errorMessage);
    }

    public static void validateForNull(Object object, String errorMessage){
        if (object == null)
            throw new NullArgumentException(errorMessage);
    }
}
