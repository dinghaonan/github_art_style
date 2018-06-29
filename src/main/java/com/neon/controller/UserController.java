package com.neon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neon.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService service;

	@RequestMapping(value = "/queryUserById", method = { RequestMethod.GET })
	public String queryUserById(String userId) {
		service.getUserById("1");
		return "";
	}

}
