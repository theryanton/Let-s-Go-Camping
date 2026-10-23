package edu.usc.csci310.project.models;

import jakarta.persistence.*;

import java.security.MessageDigest;

// This annotation is from the Java Persistence API (JPA) and
// specifies that the class is a JPA entity, meaning that it is
// mapped to a database table.
@Entity
@Table(name = "user_table")
public class User {

    public static String sha256(final String base) { // thank you to stack overflow
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    //used to compare login request data with database.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long user_id;
    private String username;
    private String password;

    public User() {

    }

    public User(String name, String pass) {
        this.username = name;
        this.password = pass;
    }

    public User(long user_id, String name, String pass) {
        this.user_id = user_id;
        this.username = name;
        this.password = pass;
    }

    public long getUser_id() {
        return user_id;
    }
    public void setUserId(long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

}
