package fi.vamk.database.entity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fi.vamk.database.entity.Profile;
import fi.vamk.database.entity.repository.ProfileRepository;

@Service
public class ProfileServiceImp implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Iterable<Profile> finAll() {
		return profileRepository.findAll();
	}

	@Override
	public Page<Profile> findAll(Pageable pageable) {
		return profileRepository.findAll(pageable);
	}

	@Override
	public Optional<Profile> findById(Long id) {
		return profileRepository.findById(id);
	}

	@Override
	public Profile save(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public void deleteById(Long Id) {
		profileRepository.deleteById(Id);
		
	}

}