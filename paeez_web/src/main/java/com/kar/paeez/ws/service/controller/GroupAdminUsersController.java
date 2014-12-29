package com.kar.paeez.ws.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kar.paeez.ws.bo.GroupAdminUsersBO;
import com.kar.paeez.ws.model.User;
import com.kar.paeez.ws.repo.mongo.UserRepository;
import com.kar.paeez.ws.response.ResponseConstants;
import com.kar.paeez.ws.response.WSResponse;

@RestController
public class GroupAdminUsersController {

	@Autowired
	private GroupAdminUsersBO groupAdminUsersBO;
	
	@Autowired
	protected UserRepository userRepo;
	
	@RequestMapping(value="/grpAUsr/add/{userId}/{grpId}")
    public List<String> addAdminUser(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId,
    		@RequestParam(value="emailAddresses", defaultValue="") String emailAddresses) {

		WSResponse response = new WSResponse() ;
		String[] allEmailAddresses = emailAddresses.split(",") ;
		groupAdminUsersBO.addAdminUsers(response, grpId, Arrays.asList(allEmailAddresses)) ;
		
		return response.getMessages() ;
    }
	
	@RequestMapping(value="/grpAUsr/get/{userId}")
	@ResponseBody
    public Object getMyAdminGroups(@PathVariable("userId") String userId) {

		WSResponse response = new WSResponse() ;
		groupAdminUsersBO.getMyAdminGroups(response, userId) ;
		if (response.get(ResponseConstants.ADMIN_GROUPS) == null ) {
			
			return response.getMessages() ;
		}
		return response.get(ResponseConstants.ADMIN_GROUPS) ;
    }
	
	
	@RequestMapping(value="/grpAUsr/user", consumes="application/json")
	@ResponseBody
    public User user(@RequestBody User user) {

		return userRepo.findByEmailAddress(user.getEmailAddress()) ;
    }

}
