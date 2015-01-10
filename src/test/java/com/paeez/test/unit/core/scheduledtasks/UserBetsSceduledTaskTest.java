package com.paeez.test.unit.core.scheduledtasks;

import com.paeez.core.model.*;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetOptions;
import com.paeez.core.services.constants.UserResult;
import com.paeez.test.base.BaseTest;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Shrikant on 1/3/15.
 */

public class UserBetsSceduledTaskTest extends BaseTest {
    @BeforeClass
    public static void beforeAll() {
        System.out.print("---Starting UserMatchBetsResultUpdateSceduledTaskTest");
    }

    @AfterClass
    public static void afterAll() {
        System.out.print("---Finished UserMatchBetsResultUpdateSceduledTaskTest");
    }
    @Before
    public void seedData() {
        //Assert.assertNotNull("Dependency Injection for MongoOperations unsuccessful", mongoOperations);

    }

    @Test
    public void testUpdateUserPlayedBetsResult() throws Exception{

        //clean up
        groupRepository.deleteAll();
        userRepository.deleteAll();
        groupUsersRepository.deleteAll();
        groupBetsRepository.deleteAll();
        userBetsRepository.deleteAll();

        //Step1: Create a group
        Group grp = new Group();
        grp.setDescription("This is Invaders");
        grp.setName("Invaders");
        grp.setStatus("active");

        groupRepository.save(grp);
        List<Group> groupList = groupRepository.findAll();
        Assert.assertNotNull("groupList cannot be null", groupList);
        for (Group g : groupList) {
            System.out.println("** Created group:" + g.getName());
        }
        String testGroupId = groupList.get(0).getId();
        //step2: Create a user
        User usr = new User();
        usr.setEmailAddress("sachin@paeez.com");
        usr.setName("Sachin");
        usr.setGroups(groupList);
        usr.setStatus("Active");

        userRepository.save(usr);

        List<User> userList = userRepository.findAll();
        Assert.assertNotNull("userList cannot be null", userList);
        for (User u : userList) {
            System.out.println("** Created user:" + u.getName());
        }
        String testUserId = userList.get(0).getId();
        String testUserEmailId = userList.get(0).getEmailAddress();

        //Step 3: Add user to group
        GroupUsers grpUser = new GroupUsers() ;
        grpUser.setGroupId(testGroupId);
        grpUser.setUserEmailAddress(testUserEmailId);
        grpUser.setAddedOn(System.currentTimeMillis());
        groupUsersRepository.save(grpUser);

        //Step 4 create a Generic bet
        GenericBet genericBet = new GenericBet();
        genericBet.setDescription("Highest run scorer");
        genericBet.setBetType(BetTypes.HIGESTSCORER);
        genericBet.setStatus(BetStatus.ACTIVE);
        genericBet.setCreatedTime(new Date());

        Map<BetOptions, String> options = new HashMap<BetOptions, String>();
        options.put(BetOptions.OPTIONA, "Kohli");
        options.put(BetOptions.OPTIONB, "Gayle");
        options.put(BetOptions.OPTIONC, "AB");
        options.put(BetOptions.OPTIOND, "Dhoni");

        genericBet.setOptions(options);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "05-08-2015 10:20:56";
        Date date = sdf.parse(dateInString);

        genericBet.setClosingTime(date);
        genericBetService.saveBet(genericBet);

        List<GenericBet> genericBets = genericBetService.findActive();
        Assert.assertNotNull("genericBets cannot be null", genericBets);
        Assert.assertEquals("Incorrect number of genericBets created", 1 ,genericBets.size());

        String testGenericBetId = genericBets.get(0).getId();

        //step 5 create a cart
        GroupBets groupBets = new GroupBets();
        groupBetsService.save(groupBets);

        List<GroupBets> groupBetses = groupBetsService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", groupBetses);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 , groupBetses.size());

        String testBetsCartId = groupBetses.get(0).getId();

        //step 6 add  bets to cart
        List<String> genericBetIds = new ArrayList<String>();
        genericBetIds.add(genericBets.get(0).getId());
        groupBetses.get(0).setGenericBetIds(genericBetIds);
        groupBetsService.save(groupBets);

        groupBetses = groupBetsService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", groupBetses);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 , groupBetses.size());

        //step 7: Import betscart into group
//        GroupBetImport groupBetImport= new GroupBetImport();
//        groupBetImport.setBetsCartId(testBetsCartId);
//        groupBetImport.setGroupId(testGroupId);
//        groupBetImport.setImportedByUserEmailAddress(testUserEmailId);
//
//        groupBetImportService.update(groupBetImport);
//        GroupBetImport updatedGroupBetImport = groupBetImportService.findByGroupId(testGroupId);
//        Assert.assertNotNull("updatedGroupBetImport cannot be null", updatedGroupBetImport);

        //step 8: User puts the bet
        UserBets userBets = new UserBets();
        userBets.setUserId(testUserId);
        userBets.setGenericBetId(testGenericBetId);
        userBets.setGroupId(testGroupId);
        

        // userBets.setChoice(BetOptions.OPTIONA);
        userBetsService.putBet(userBets);

        userBets = null;
        userBets = userBetsService.findByGenericBetId(testGenericBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userBets);

        //step 9: **This is admin task, update the bet

        genericBetService.updateResult(testGenericBetId, BetOptions.OPTIONA);
        genericBetService.updateStatus(testGenericBetId, BetStatus.CLOSED);

        List<GenericBet> closedGenericBets = genericBetService.findClosed();
        Assert.assertNotNull("closedGenericBets cannot be null", groupBetses);
        Assert.assertEquals("Incorrect number of closedGenericBets created", 1 ,closedGenericBets.size());

        //Step 10: the trigger scheduler task should have updated the user results in UserMatchBets
        Thread.sleep(5000); //sleep for 5 secs for the scheduled task to run and complete
        userBets = userBetsService.findByGenericBetId(testGenericBetId);
        Assert.assertNotNull("userPlayedBets cannot be null", userBets);
        System.out.println(userBets);
        Assert.assertNotNull("user bet results not updated by task", userBets.getUserResult());
        Assert.assertEquals("Incorrect user bet results", userBets.getUserResult(), UserResult.WON);
    }

}
