package edu.usc.csci310.project.controllers;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User temp = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedUsername = User.sha256(user.getUsername());

        temp.setUsername(hashedUsername); //Did this for now because we'll need a new way of retrieving users

        temp.setPassword(passwordEncoder.encode(user.getPassword()));

        //modified because it was sending a message including hashed password after registration
        if (userRepo.findByUsername(temp.getUsername()).isPresent()) {
            // Username already exists
            return new ResponseEntity<>("That username has already been taken", HttpStatus.BAD_REQUEST);
        }

        userRepo.save(temp);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}