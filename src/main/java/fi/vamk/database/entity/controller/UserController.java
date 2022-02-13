package fi.vamk.database.entity.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.vamk.database.entity.User;
import fi.vamk.database.entity.service.UserService;

@RestController														//devuelve por defecto un Json
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//*********CRUD********
	
	//create a new user
		@PostMapping
		public ResponseEntity<?> create(@RequestBody User user){
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
		}
	
	//read a user
		@GetMapping("/{id}")									// id esta entre corchetes por que es variable
		public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
			Optional<User> oUser = userService.findById(userId);
			if(!oUser.isPresent()) {
				return ResponseEntity.notFound().build();
			} 
			
			return ResponseEntity.ok(oUser);
		}
	
	//update user
		@PutMapping("/{id}")
		public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId){
			Optional<User> oUser = userService.findById(userId);
			if(!oUser.isPresent()) {
				return ResponseEntity.notFound().build();
			} 
			
			oUser.get().setName(userDetails.getName());
			oUser.get().setPhone(userDetails.getPhone());
			oUser.get().setEmail(userDetails.getEmail());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		}
		
	//delete user
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
			Optional<User> oUser = userService.findById(userId);
			if(!oUser.isPresent()) {
				return ResponseEntity.notFound().build();
			} 
			
			userService.deleteById(userId);
			return ResponseEntity.ok().build();
		}
	
	//read all
		@GetMapping									
		public List<User> readAll(){
			List<User> users = StreamSupport
					.stream(userService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			return users;
		}
}