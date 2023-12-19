package company.bloggingplatform.controller;

import company.bloggingplatform.config.security.JwtTokenUtil;
import company.bloggingplatform.dto.request.JWTSignUpRequestDto;
import company.bloggingplatform.dto.request.JwtSignInRequestDto;
import company.bloggingplatform.dto.response.JwtResponseDto;
import company.bloggingplatform.entity.UserEntity;
import company.bloggingplatform.enumuration.Role;
import company.bloggingplatform.exception.handler.SuccessDetails;
import company.bloggingplatform.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping()
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class JwtAuthenticationController {


	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsService jwtInMemoryUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepo;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> signIn(@Valid @RequestBody JwtSignInRequestDto request, BindingResult bindingResult)
			throws Exception {

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder errorMsg = new StringBuilder("Validation error(s): ");
			for (FieldError error : errors) {
				errorMsg.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMsg);
		}

		authenticate(request.getMail(), request.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(request.getMail());


		String role = String.valueOf(request.getRole());
		final String token = jwtTokenUtil.generateToken(userDetails, role);
		JwtResponseDto signInResponseDto = JwtResponseDto.builder()
				.token(token)
				.mail(request.getMail())
				.role(request.getRole())
				.build();

		return  ResponseEntity.ok(new SuccessDetails<>(signInResponseDto, HttpStatus.OK.value(), true));
	}

	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public ResponseEntity<?> signUp (@Valid @RequestBody JWTSignUpRequestDto dto, BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder errorMsg = new StringBuilder("Validation error(s): ");
			for (FieldError error : errors) {
				errorMsg.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMsg);
		}

		UserEntity entity = userRepo.findUsersEntityByMail(dto.getMail());
		if (entity == null) {
			UserEntity userEntity = UserEntity.builder()
					.mail(dto.getMail())
					.username(dto.getUsername())
					.password(passwordEncoder.encode(dto.getPassword()))
					.role(dto.getRole())
					.build();
			userRepo.save(userEntity);
			return ResponseEntity.ok("You signed!");
		}else
			return ResponseEntity.ok("This account already exist in our DB!");

	}



	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
