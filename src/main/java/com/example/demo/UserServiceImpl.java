package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
	
     @Override
     public User insertUser(User user) {
         //encode the password
//         String password = user.getPassword();
//
//         String hashedPassword = BCrypt.hashpw(
//                 user.getPassword(),
//                 BCrypt.gensalt()
//         );
//
//         user.setPassword(hashedPassword);
//         return this.userRepository.save(user);
    	 
    	 User createdUser= this.userRepository.save(user);
    	 return createdUser;
     }
}
