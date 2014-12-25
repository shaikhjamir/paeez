package com.kar.paeez.ws.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kar.paeez.ws.bo.GroupAdminUsersBO;
import com.kar.paeez.ws.response.WSResponse;

@RestController
public class GroupAdminUsersController {

	@Autowired
	private GroupAdminUsersBO groupAdminUsersBO;
	
	@RequestMapping("/grpAUsr/add/{userId}/{grpId}")
    public List<String> addAdminUser(@PathVariable("userId") String userId, @PathVariable("grpId") String grpId,
    		@RequestParam(value="emailAddresses", defaultValue="") String emailAddresses) {

		WSResponse response = new WSResponse() ;;
		String[] allEmailAddresses = emailAddresses.split(",") ;
		groupAdminUsersBO.addAdminUsers(response, grpId, Arrays.asList(allEmailAddresses)) ;
		return response.getMessages() ;
    }
}
