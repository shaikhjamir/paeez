package com.paeez.rest.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WSResponse {

	private List<String> messages = new ArrayList<>() ;
	
	private Map<Object,Object> responseMap = new HashMap<>() ;
	
	public void info(String message) {
		
		messages.add("INFO: " + message) ;
	}
	
	public void error(String message) {
			
			messages.add("ERROR: " + message) ;
		}
	
	public void warn(String message) {
		
		messages.add("WARN: " + message) ;
	}

	public List<String> getMessages() {
		
		return messages ;
	}
	
	public void put(Object key,Object value) {
		
		responseMap.put(key, value) ;
	}
	
	public Object get(Object key) {
		
		return responseMap.get(key) ;
	}

	public Map<Object,Object> getResponse() {
		
		return responseMap ;
	}
}
