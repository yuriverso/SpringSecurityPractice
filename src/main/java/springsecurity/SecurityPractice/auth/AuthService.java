package springsecurity.SecurityPractice.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springsecurity.SecurityPractice.appuser.AppUser;
import springsecurity.SecurityPractice.appuser.Role;
import springsecurity.SecurityPractice.appuser.UserRepo;
import springsecurity.SecurityPractice.jwt.JwtService;

@Service @RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	
	public AuthResponse register(RegisterRequest request) {
		AppUser user = AppUser.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
		userRepo.save(user);
		
		String jwt = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwt).build();
		
	}
	
	public AuthResponse authenticate(AuthRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		AppUser user = userRepo.findByEmail(request.getEmail()).orElseThrow();
		
		String jwt = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwt).build();
	}
	
}
