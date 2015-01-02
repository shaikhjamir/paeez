package com.paeez.rest.controllers;

import com.paeez.core.services.impl.GroupAdminUsersService;
import com.paeez.core.model.User;
import com.paeez.core.repositories.mongo.UserRepository;
import com.paeez.rest.responses.ResponseConstants;
import com.paeez.rest.responses.WSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class GroupAdminUsersController {

	@Autowired
	private GroupAdminUsersService groupAdminUsersBO;
	
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
	
	@RequestMapping(value="/grpAUsr/remove/{userId}/{grpId}")
	@ResponseBody
    public Object remove(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId,
    		@RequestParam(value="emailAddresses", defaultValue="") String emailAddresses) {

		WSResponse response = new WSResponse() ;
		String[] allEmailAddresses = emailAddresses.split(",") ;
		groupAdminUsersBO.removeAdminUsers(response, grpId, Arrays.asList(allEmailAddresses), userId) ;
		return response.getMessages() ;
    }

	@RequestMapping(value="/grpAUsr/getMyAdminGrps/{userId}")
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
