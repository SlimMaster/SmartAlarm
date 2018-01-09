package com.spring.test.security.RestService;



import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.spring.test.RestService.SimpleEmailSender;
import com.spring.test.entities.Produit;
import com.spring.test.security.ConfirmRegistration.VerificationTokenRepository;
import com.spring.test.security.model.security.User;
import com.spring.test.security.model.security.VerificationToken;
import com.spring.test.security.security.JwtTokenUtil;
import com.spring.test.security.security.repository.UserRepository;
import com.spring.test.security.security.service.JwtAuthenticationResponse;



@RestController
public class UserRestService {

	@Autowired
	private UserRepository userRepository ;
	
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    ApplicationEventPublisher eventPublisher;
    
    @Autowired
    SimpleEmailSender simpleEmailSender;
    
//    @Autowired
//    VerificationToken verificationToken;
//    
    @Autowired
    VerificationTokenRepository tokenRepository ;
   
    
	@RequestMapping(value="/users/register" , method = RequestMethod.POST) 
	public User addUser(@RequestBody User u) throws Exception  {
		
//		System.out.print("je suis laaaaaa");
		if (userRepository.findByEmail(u.getEmail()) != null ) {
			return null;
		}
		
		String noDecrypted = u.getPassword();
		u.setPassword(passwordEncoder.encode(noDecrypted));
		//debut mail confirmation logic
		User registered = userRepository.save(u); //user sauvegardé mais non activé (enabled =0 )
		/// créer Token de confirmation de l'email
		String token = UUID.randomUUID().toString();
		VerificationToken myToken = new VerificationToken(token,u,new Date());
		System.out.println(myToken);
		System.out.println(myToken.getUser());
		tokenRepository.save(myToken);
		simpleEmailSender.sendEmail(u.getEmail(),token);
		//end
		
		return registered; 
		
		
	}
	
	@RequestMapping(value="/users/{id}" , method = RequestMethod.PUT)
	public User updateUser(@PathVariable("id") Long id, @RequestBody User u) {
		User concernedUser = userRepository.findOne(id);
		concernedUser.setNom(u.getNom());
		concernedUser.setPrenom(u.getPrenom());
		
		return userRepository.saveAndFlush(concernedUser);
	}
	
	
	@RequestMapping(value="/users/ConfirmRegistration/{token}") 
	public void ConfirmAddUser(@PathVariable("token") String token)  {
		//String realMail = mail+".slim@gmail.com";
		System.out.println(token);
		User u = tokenRepository.findByToken(token).getUser();
		System.out.println(u.getPrenom());
		System.out.println(u.getEnabled());
		
		u.setEnabled(1);
		System.out.println(u.getEnabled());
		userRepository.saveAndFlush(u);
		System.out.println(u.getEnabled());
		

		
		
	}
	@RequestMapping(value="/users/{username}", method = RequestMethod.GET) 
	public User showUser(@PathVariable("username") String username)  {
		
		System.out.println(username);
		return userRepository.findByUsername(username);
		
	}
	
	

}
