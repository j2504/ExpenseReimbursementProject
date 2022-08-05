package com.revature.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.exception.ResourceNotFoundException;
import com.revature.ecommerce.model.Users;
import com.revature.ecommerce.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/g-corp")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public Users createUser(@RequestBody Users user) {
		return userRepository.save(user);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this is: " + userId));

		user.setEmail(userDetails.getEmail());
		user.setRole(userDetails.getRole());
		user.setEnabled(userDetails.isEnabled());
		final Users updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));

		userRepository.deleteById(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/users/{email}")
	public List<Users> getUsersByEmail(@PathVariable("email") String email) {
		return userRepository.findByEmail(email);
	}
	
	
}
