package com.example.braulio.userapi.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserSevice userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(requiered = false) String sorteBy,
			@RequestParam(requiered = false) String filter) {
		if (filter != null) {
			return ResponseEntity.ok(userService.filterUsers(filter));
		}
		return ResponseEntity.ok(userService.getUsersSortedBy(sorteBy));
		
		@PostMapping
		public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
			return 
					ResponseEntity.status(HttpStatus.CREATED).body(userService.creaUser(userDTO));
			
			
		}
		@PatchMapping("/{id}")
		public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody)
		Map<String, Object> updates){
			return ResponseEntity.ok(userService.updateUser(id,updates));
		}
		@DeleteMapping
		public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
			
		}
	}

}
