package fi.vamk.database.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.vamk.database.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}