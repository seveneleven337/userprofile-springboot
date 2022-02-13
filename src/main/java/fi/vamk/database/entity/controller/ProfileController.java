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

import fi.vamk.database.entity.Profile;
import fi.vamk.database.entity.User;
import fi.vamk.database.entity.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	//*********CRUD********
	
		//create a new user
			@PostMapping
			public ResponseEntity<?> create(@RequestBody Profile profile){
				return ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(profile));
			}
		
		//read a user
			@GetMapping("/{id}")									// id esta entre corchetes por que es variable
			public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
				Optional<Profile> oProfile = profileService.findById(userId);
				if(!oProfile.isPresent()) {
					return ResponseEntity.notFound().build();
				} 
				
				return ResponseEntity.ok(oProfile);
			}
		
		//update user
			@PutMapping("/{id}")
			public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId){
				Optional<Profile> oProfile = profileService.findById(userId);
				if(!oProfile.isPresent()) {
					return ResponseEntity.notFound().build();
				} 
				
				oProfile.get().setName(userDetails.getName());
				
				return ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(oProfile.get()));
			}
			
		//delete user
			@DeleteMapping("/{id}")
			public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
				Optional<Profile> oProfile = profileService.findById(userId);
				if(!oProfile.isPresent()) {
					return ResponseEntity.notFound().build();
				} 
				
				profileService.deleteById(userId);
				return ResponseEntity.ok().build();
			}
		
		//read all
			@GetMapping									
			public List<Profile> readAll(){
				List<Profile> profile = StreamSupport
						.stream(profileService.finAll().spliterator(), false)
						.collect(Collectors.toList());
				return profile;
			}
	
}