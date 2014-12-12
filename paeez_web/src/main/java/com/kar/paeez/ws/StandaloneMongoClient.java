package com.kar.paeez.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.kar.paeez.ws.model.Person;

public class StandaloneMongoClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericXmlApplicationContext(
				"paeez_mongodb.xml");
		MongoOperations mo = (MongoOperations) ctx
				.getBean("paeez_mongoTemplate");
		Person a = new Person();
		a.setFirstName("Jaa");
		a.setLastName("Shaikhhh");

		if (!mo.collectionExists(Person.class)) {

			mo.createCollection(Person.class);
		}
		mo.save(a);
	}
}