package com.paeez.rest.resources.asm;


import com.paeez.rest.controllers.MatchBetController;
import com.paeez.core.model.MatchBet;
import com.paeez.rest.resources.MatchBetResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Shrikant on 12/21/14.
 */
public class MatchBetResourceAsm extends ResourceAssemblerSupport<MatchBet, MatchBetResource> {
    public MatchBetResourceAsm() {
        super(MatchBetController.class, MatchBetResource.class);
    }
    @Override
    public MatchBetResource toResource(MatchBet matchBet) {
        MatchBetResource resource = new MatchBetResource();
        resource.setIconTeam(matchBet.getIconTeam());
        resource.setChallengerTeam(matchBet.getChallengerTeam());
        resource.setBetType(matchBet.getBetType());
        resource.setStatus(matchBet.getStatus());
        resource.setBetWinner(matchBet.getBetWinner());

        // Convert Date to String
        SimpleDateFormat sdf = new SimpleDateFormat();
        String dateString = sdf.format(matchBet.getMatchDate());

        resource.setMatchDate(dateString);
        resource.add(linkTo(MatchBetController.class).slash(matchBet.getId()).slash("betinfo").withSelfRel());
        return resource;
    }
}
