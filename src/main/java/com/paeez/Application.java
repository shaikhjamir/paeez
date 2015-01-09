package com.paeez;

/**
 * Created by Shrikant on 12/31/14.
 */


import com.paeez.core.model.BetsCart;
import com.paeez.core.repositories.mongo.BetsCartRepository;
import com.paeez.core.services.constants.BetStatus;
import com.paeez.core.services.constants.BetTypes;
import com.paeez.core.services.constants.BetOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private BetsCartRepository betsCartRepository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Override
    public void run(String... args) throws Exception {

//
//        betsCartRepository.deleteAll();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        String dateInString = "31-08-1982 10:20:56";
//        Date date = sdf.parse(dateInString);
//
//
//        dateInString = "30-09-1982 10:20:56";
//        date = sdf.parse(dateInString);
//
//        matchBet2.setMatchDate(date);
//        // save a couple of customers
//        repository.save(matchBet);
//        repository.save(matchBet2);
//
//        BetsCart betsCart = new BetsCart();
//        List<Bet> betsList = new ArrayList<Bet>();
//        // fetch all customers
//        System.out.println("Bets found with findAll():");
//        System.out.println("-------------------------------");
//        for (MatchBet bets : repository.findAll()) {
//            System.out.println(bets);
//            betsList.add(bets);
//
//
//            System.out.println("Bets found with findById(id):");
//            System.out.println("--------------------------------");
//            System.out.println(repository.findById(bets.getId()));
//        }
//        betsCart.setBets(betsList);
//        betsCartRepository.save(betsCart);
//        System.out.println();
//        System.out.println();
//
//        for (BetsCart cart : betsCartRepository.findAll()) {
//            System.out.println("BetsCart found with findAll():");
//            System.out.println("-------------------------------");
//            System.out.println(cart);
//            System.out.println();
//            System.out.println("BetsCart found with findById(id):");
//            System.out.println("--------------------------------");
//            System.out.println(betsCartRepository.findById(cart.getId()));
//        }
//        System.out.println();
    }

}