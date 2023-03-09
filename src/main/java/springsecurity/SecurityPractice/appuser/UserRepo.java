package springsecurity.SecurityPractice.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Integer>{
	Optional<AppUser> findByEmail(String email);
}
