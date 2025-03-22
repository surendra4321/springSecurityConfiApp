package com.SecurityApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecurityApplication.model.User;
import com.SecurityApplication.servies.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ADMIN')")  //PreAuthorized will check the authorization before executing the method based on role
	@GetMapping("/public")
	public String publicEndpoint() {
		return "This the public api";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();

	}

	// getting the single user
	@GetMapping("/{username}")
	public List<User> getUser(@PathVariable("username") String username) {
		return this.userService.getAllUsers();
	}

	// Creating the API to add the User inside the List
	@PostMapping("/")
	public User add(@RequestBody User user) {
		return this.userService.addUser(user);
	}

}
