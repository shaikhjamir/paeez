package com.kar.paeez.ws.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kar.paeez.ws.model.User;

@RestController
public class GreetingController {

	@RequestMapping("/greeting")
    public User greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
		User person = new User() ;
    	person.setName(name);
    	person.setEmailAddress(name + "@gmail.com");
    	return person ;
    }
}
