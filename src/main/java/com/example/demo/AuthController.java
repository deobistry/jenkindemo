package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.LoginCredentials;
import com.example.demo.dtos.TokenResponse;
import com.example.demo.jwt.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService us;
	
	@Autowired
	private UserRepository ur;
	
	  @PostMapping("/signup")
	    public ResponseEntity<User> signup(@ModelAttribute User user) {
	        return new ResponseEntity<>(us.createUser(user), HttpStatus.CREATED);
	    }
	  
	  @PostMapping("/login")
	  public ResponseEntity <TokenResponse> login(@ModelAttribute LoginCredentials credentials) {
		  User foundUser= this.ur.findByEmail(credentials.getEmail());
		  System.out.println(foundUser);
		  
		  if(foundUser!= null && foundUser.getPassword().equals(credentials.getPassword())) {
			  String token= JwtUtils.generateToken(foundUser.getId(), foundUser.getName());
			  System.out.println(token);
			  TokenResponse response= new TokenResponse(token);
			  
			  return ResponseEntity.ok(response);
			  
		  }
		  return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		  
	  }
}
