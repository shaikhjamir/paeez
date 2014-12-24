package com.kar.paeez.ws;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.kar.paeez.ws.model.Group;
import com.kar.paeez.ws.model.User;

public class StandaloneMongoClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericXmlApplicationContext(
				"paeez_mongodb.xml");
		MongoOperations mo = (MongoOperations) ctx
				.getBean("paeez_mongoTemplate");
		
		
		User usr1 = new User() ;
		usr1.setName("Jamir");
		usr1.setEmailAddress("shaikhjamir@gmail.com");
		usr1.setCreatedTime(System.currentTimeMillis());
		usr1.setLastActivity(System.currentTimeMillis());
		usr1.setLastLoggedIn(System.currentTimeMillis());
		
		
		User usr2 = new User() ;
		usr2.setName("Jamir");
		usr2.setEmailAddress("shaikhjamir2@gmail.com");
		usr2.setCreatedTime(System.currentTimeMillis());
		usr2.setLastActivity(System.currentTimeMillis());
		usr2.setLastLoggedIn(System.currentTimeMillis());
		
		
		if (!mo.collectionExists(User.class)) {

			mo.createCollection(User.class);
		}
		mo.save(usr1);
		mo.save(usr2);
		

		Group firstGroup = new Group() ;
		firstGroup.setCreatedTime(System.currentTimeMillis());
		firstGroup.setDescription("This is a test group");
		firstGroup.setName("Test--1");
		firstGroup.setStatus("ACTIVE");
		ArrayList<User> users = new ArrayList<>() ;
		users.add(usr1) ;
		users.add(usr2) ;
		
		firstGroup.setUsers(users);
		firstGroup.setAdminUsers(users);
		
		if (!mo.collectionExists(Group.class)) {

			mo.createCollection(Group.class);
		}
		mo.save(firstGroup);
	
	}
	
	
	
}