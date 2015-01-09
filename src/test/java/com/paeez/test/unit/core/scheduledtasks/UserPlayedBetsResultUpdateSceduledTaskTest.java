package com.paeez.test.unit.core.scheduledtasks;

import com.paeez.core.model.*;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.constants.UserResult;
import com.paeez.test.base.BaseTest;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Shrikant on 1/3/15.
 */

public class UserPlayedBetsResultUpdateSceduledTaskTest extends BaseTest {
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
    public void testUpdateUserMatchBetsResult() throws Exception{

        //clean up
        groupRepository.deleteAll();
        userRepository.deleteAll();
        groupUsersRepository.deleteAll();
        matchBetRepository.deleteAll();
        betsCartRepository.deleteAll();
        userPlayedBetsRepository.deleteAll();

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

        //Step 4 create a match bet
        MatchBet matchBet = new MatchBet();
        matchBet.setIconTeam("India");
        matchBet.setChallengerTeam("Australia");
        matchBet.setBetType(BetTypes.MATCHBET);
        matchBet.setStatus(BetStatus.ACTIVE);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "05-08-2015 10:20:56";
        Date date = sdf.parse(dateInString);

        matchBet.setMatchDate(date);
        matchBetService.enterBet(matchBet);

        List<MatchBet> matchBets = matchBetService.findActive();
        Assert.assertNotNull("matchBets cannot be null", matchBets);
        Assert.assertEquals("Incorrect number of matchBets created", 1 ,matchBets.size());

        String testMatchBetId = matchBets.get(0).getId();

        //step 5 create a cart
        BetsCart betsCart = new BetsCart();
        betsCartService.save(betsCart);

        List<BetsCart> betsCarts = betsCartService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 ,betsCarts.size());

        String testBetsCartId = betsCarts.get(0).getId();

        //step 6 add match bet to cart
        List<Bet> betsList = new ArrayList<Bet>();
        betsList.add(matchBet);
        betsCarts.get(0).setBets(betsList);
        betsCartService.save(betsCart);

        betsCarts = betsCartService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 ,betsCarts.size());

        //step 7: Import betscart into group
        GroupBetImport groupBetImport= new GroupBetImport();
        groupBetImport.setBetsCartId(testBetsCartId);
        groupBetImport.setGroupId(testGroupId);
        groupBetImport.setImportedByUserEmailAddress(testUserEmailId);

        groupBetImportService.update(groupBetImport);
        GroupBetImport updatedGroupBetImport = groupBetImportService.findByGroupId(testGroupId);
        Assert.assertNotNull("updatedGroupBetImport cannot be null", updatedGroupBetImport);

        //step 8: User puts the bet
        UserPlayedBets userPlayedBets = new UserPlayedBets();
        userPlayedBets.setUserId(testUserId);
        userPlayedBets.setGenericBetId(testMatchBetId);
        userPlayedBets.setGroupId(testGroupId);
        userPlayedBets.setBetsCartId(testBetsCartId);

        userPlayedBets.setChoice(BetWinner.ICONTEAM);
        userPlayedBetsService.putBet(userPlayedBets);

        userPlayedBets = null;
        userPlayedBets = userPlayedBetsService.findByGenericBetId(testMatchBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userPlayedBets);

        //step 9: **This is admin task, update the bet

        matchBetService.updateResult(testMatchBetId, BetWinner.ICONTEAM);
        matchBetService.updateStatus(testMatchBetId, BetStatus.CLOSED);

        List<MatchBet> closedMatchBets = matchBetService.findClosed();
        Assert.assertNotNull("closedMatchBets cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of closedMatchBets created", 1 ,closedMatchBets.size());

        //Step 10: the trigger scheduler task should have updated the user results in UserMatchBets
        Thread.sleep(5000); //sleep for 5 secs for the scheduled task to run and complete
        userPlayedBets = userPlayedBetsService.findByGenericBetId(testMatchBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userPlayedBets);
        System.out.println(userPlayedBets);
        Assert.assertNotNull("user bet results not updated by task", userPlayedBets.getUserResult());
        Assert.assertEquals("Incorrect user bet results", userPlayedBets.getUserResult(), UserResult.WON);
    }

    @Test
    public void testUpdateUserPlayedBetsResult() throws Exception{

        //clean up
        groupRepository.deleteAll();
        userRepository.deleteAll();
        groupUsersRepository.deleteAll();
        matchBetRepository.deleteAll();
        betsCartRepository.deleteAll();
        userPlayedBetsRepository.deleteAll();

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

        Map<String, String> options = new HashMap<String, String>();
        options.put("optionA", "Kohli");
        options.put("optionB", "Gayle");
        options.put("optionC", "AB");
        options.put("optionD", "Dhoni");

        genericBet.setOptions(options);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "05-08-2015 10:20:56";
        Date date = sdf.parse(dateInString);

        genericBet.setClosingTime(date);
        genericBetService.enterBet(genericBet);

        List<GenericBet> genericBets = genericBetService.findActive();
        Assert.assertNotNull("genericBets cannot be null", genericBets);
        Assert.assertEquals("Incorrect number of genericBets created", 1 ,genericBets.size());

        String testGenericBetId = genericBets.get(0).getId();

        //step 5 create a cart
        BetsCart betsCart = new BetsCart();
        betsCartService.save(betsCart);

        List<BetsCart> betsCarts = betsCartService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 ,betsCarts.size());

        String testBetsCartId = betsCarts.get(0).getId();

        //step 6 add match bet to cart
        List<Bet> betsList = new ArrayList<Bet>();
        betsList.add(genericBets.get(0));
        betsCarts.get(0).setBets(betsList);
        betsCartService.save(betsCart);

        betsCarts = betsCartService.findAll();
        Assert.assertNotNull("betsCarts cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 ,betsCarts.size());

        //step 7: Import betscart into group
        GroupBetImport groupBetImport= new GroupBetImport();
        groupBetImport.setBetsCartId(testBetsCartId);
        groupBetImport.setGroupId(testGroupId);
        groupBetImport.setImportedByUserEmailAddress(testUserEmailId);

        groupBetImportService.update(groupBetImport);
        GroupBetImport updatedGroupBetImport = groupBetImportService.findByGroupId(testGroupId);
        Assert.assertNotNull("updatedGroupBetImport cannot be null", updatedGroupBetImport);

        //step 8: User puts the bet
        UserPlayedBets userPlayedBets = new UserPlayedBets();
        userPlayedBets.setUserId(testUserId);
        userPlayedBets.setGenericBetId(testGenericBetId);
        userPlayedBets.setGroupId(testGroupId);
        userPlayedBets.setBetsCartId(testBetsCartId);

        userPlayedBets.setChoice(BetWinner.OPTIONA);
        userPlayedBetsService.putBet(userPlayedBets);

        userPlayedBets = null;
        userPlayedBets = userPlayedBetsService.findByGenericBetId(testGenericBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userPlayedBets);

        //step 9: **This is admin task, update the bet

        genericBetService.updateResult(testGenericBetId, BetWinner.OPTIONA);
        genericBetService.updateStatus(testGenericBetId, BetStatus.CLOSED);

        List<GenericBet> closedGenericBets = genericBetService.findClosed();
        Assert.assertNotNull("closedGenericBets cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of closedGenericBets created", 1 ,closedGenericBets.size());

        //Step 10: the trigger scheduler task should have updated the user results in UserMatchBets
        Thread.sleep(5000); //sleep for 5 secs for the scheduled task to run and complete
        userPlayedBets = userPlayedBetsService.findByGenericBetId(testGenericBetId);
        Assert.assertNotNull("userPlayedBets cannot be null", userPlayedBets);
        System.out.println(userPlayedBets);
        Assert.assertNotNull("user bet results not updated by task", userPlayedBets.getUserResult());
        Assert.assertEquals("Incorrect user bet results", userPlayedBets.getUserResult(), UserResult.WON);
    }

}
