package edu.usc.csci310.project.controllers;

import edu.usc.csci310.project.models.User;
import edu.usc.csci310.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

//@SuppressWarnings("ALL")
@RestController
public class LoginController {

    //NEED TO REWRITE TO MATCH REQUESTED FUNCTIONALITY

    @Autowired
    private UserRepository userRepository;

    private static int loginAttempts = 0;

    public static void setLoginAttempts(int attempt) {
        loginAttempts = attempt;
    }

    public static int getLoginAttempts() {
        return loginAttempts;
    }

    private static long firstAttemptTime = 0;
    public static void setFirstAttemptTime(long attempt) {
        firstAttemptTime = attempt;
    }

    private static long lastAttemptTime = 0;
    public static void setLastAttemptTime(long attempt) {
        lastAttemptTime = attempt;
    }

    public boolean accountBlock(int loginAttempts) {
        lastAttemptTime = System.currentTimeMillis();
        return System.currentTimeMillis() - firstAttemptTime <= 60000 && loginAttempts >= 3;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        if (loginAttempts == 0 && System.currentTimeMillis() - firstAttemptTime >= 60000) { //track login attempts
             firstAttemptTime = System.currentTimeMillis();
        }

        if ( (System.currentTimeMillis() - lastAttemptTime) >= 30000) { //check for 30 second attempts reset
            loginAttempts = 0;
        }

        if (accountBlock(loginAttempts)) { //if user has been blocked, then don't process login request
            return ResponseEntity.status(401).body(Map.of("message","Account Blocked","user_id",-1));
        }

        Optional<User> find_user = userRepository.findByUsername(User.sha256(user.getUsername()));

        //in any case where the username is invalid, the password is incorrect, or too many login attempts have been made,
        //the controller will send back the appropriate message and a user_id of -1.

        if (find_user.isPresent()) {
            User foundUser = find_user.get();
            System.out.println(foundUser.getUser_id());
            //Add hash function for password storage. Compare hash of entered password and retrieved hash
            if (BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
                loginAttempts = 0;
                return ResponseEntity.ok().body(Map.of("message","Login Successful","user_id",foundUser.getUser_id()));
            }
            else { //incorrect password
                loginAttempts += 1;
                if (accountBlock(loginAttempts)) {
                    return ResponseEntity.status(401).body(Map.of("message","Account Blocked","user_id",-1));
                }
                return ResponseEntity.badRequest().body(Map.of("message","Incorrect password","user_id",-1));
            }
        }
        else { //invalid username. if too many login attempts have been made then block. else return invalid username message.
            loginAttempts += 1;
            if (accountBlock(loginAttempts)) {
                return ResponseEntity.status(401).body(Map.of("message","Account Blocked","user_id",-1));
            }
            return ResponseEntity.badRequest().body(Map.of("message","Invalid username","user_id",-1));
        }
    }
}
