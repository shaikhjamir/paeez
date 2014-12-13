package com.kar.paeez.ws.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kar.paeez.ws.model.Person;

@RestController
public class GreetingController {

	@RequestMapping("/greeting")
    public Person greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
    	Person person = new Person() ;
    	person.setFirstName(name);
    	person.setLastName("" + System.currentTimeMillis() );
    	return person ;
    }
}
