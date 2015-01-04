package com.paeez.test.core.scheduledtasks;

import com.paeez.core.model.*;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetWinner;
import com.paeez.core.services.constants.UserResult;
import com.paeez.test.base.Base;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shrikant on 1/3/15.
 */

public class UserMatchBetsResultUpdateSceduledTaskTest extends Base {
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
        userMatchBetsRepository.deleteAll();

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
        betsCartServices.save(betsCart);

        List<BetsCart> betsCarts = betsCartServices.findAll();
        Assert.assertNotNull("betsCarts cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of betsCarts created", 1 ,betsCarts.size());

        String testBetsCartId = betsCarts.get(0).getId();

        //step 6 add match bet to cart
        List<Bet> betsList = new ArrayList<Bet>();
        betsList.add(matchBet);
        betsCarts.get(0).setBets(betsList);
        betsCartServices.save(betsCart);

        betsCarts = betsCartServices.findAll();
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
        UserMatchBets userMatchBets = new UserMatchBets();
        userMatchBets.setUserId(testUserId);
        userMatchBets.setMatchBetId(testMatchBetId);
        userMatchBets.setGroupId(testGroupId);
        userMatchBets.setBetsCartId(testBetsCartId);

        userMatchBets.setChoice(BetWinner.ICONTEAM);
        userMatchBetsService.putBet(userMatchBets);

        userMatchBets = null;
        userMatchBets = userMatchBetsService.findByMatchBetId(testMatchBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userMatchBets);

        //step 9: **This is admin task, update the bet

        matchBetService.updateResult(testMatchBetId, BetWinner.ICONTEAM);
        matchBetService.updateStatus(testMatchBetId, BetStatus.CLOSED);

        List<MatchBet> closedMatchBets = matchBetService.findClosed();
        Assert.assertNotNull("closedMatchBets cannot be null", betsCarts);
        Assert.assertEquals("Incorrect number of closedMatchBets created", 1 ,closedMatchBets.size());

        //Step 10: the trigger scheduler task should have updated the user results in UserMatchBets
        Thread.sleep(5000); //sleep for 5 secs for the scheduled task to run and complete
        userMatchBets = userMatchBetsService.findByMatchBetId(testMatchBetId);
        Assert.assertNotNull("userMatchBets cannot be null", userMatchBets);
        System.out.println(userMatchBets);
        Assert.assertNotNull("user bet results not updated by task", userMatchBets.getUserResult());
        Assert.assertEquals("Incorrect user bet results", userMatchBets.getUserResult(), UserResult.WON);
    }
}
