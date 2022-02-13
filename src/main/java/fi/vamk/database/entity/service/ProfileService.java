package fi.vamk.database.entity.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fi.vamk.database.entity.Profile;



public interface ProfileService {

	public Iterable<Profile> finAll();
	
	public Page<Profile> findAll(Pageable pageable);
	
	public Optional<Profile> findById(Long id);
	
	public Profile save(Profile profile);
	
	public void deleteById(Long Id);
}