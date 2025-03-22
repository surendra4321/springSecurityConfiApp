package com.SecurityApplication.servies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SecurityApplication.model.User;

@Service
public class UserService {

	List<User> list = new ArrayList<>();

	public UserService() {
		list.add(new User("abc", "abc", "ABCD@gmail.com"));
		list.add(new User("ishan", "ishan123", "ishan@gmail.com"));

	}

	// get all users
	public List<User> getAllUsers() {
		return this.list;
	}

	// get single user
	public User getUser(String username) {
		return this.list.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);
	}

	// add the new user
	public User addUser(User user) {
		this.list.add(user);
		return user;

	}

}
