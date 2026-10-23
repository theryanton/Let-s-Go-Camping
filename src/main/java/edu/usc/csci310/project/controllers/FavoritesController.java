package edu.usc.csci310.project.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usc.csci310.project.models.*;
import edu.usc.csci310.project.repositories.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
public class FavoritesController {

    Dotenv dotenv = Dotenv.configure().filename("apikey.env").load();
    String apiKey = dotenv.get("API_KEY");
    String parkApiURL = "https://developer.nps.gov/api/v1/parks";

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserRepository userRepository;

    private FavoritesList userFavorites;

    private Long compareTarget = null;

    private Long myId = null;

    public long getUserCount() {
        return userRepository.count();
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/find-user")
    public ResponseEntity<?> findUser(@RequestParam String username, @RequestParam long user_id) {
        System.out.println(user_id + " is searching for " + username);
        Optional<User> user = userRepository.findByUsername(User.sha256(username));
        if (user.isPresent()) {

            if (user_id == user.get().getUser_id()) {
                return ResponseEntity.badRequest().body(Map.of("message","Don't search for yourself."));
            }
            else {
                compareTarget = user.get().getUser_id();
                return compareFavorites(); //user does exist. Send back list union.
            }

        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    public Park getParkInfo(String parkName) {
        try {

            String queryUrl = parkApiURL;
            queryUrl += "?q=";
            queryUrl += parkName.replace(" ", "%20");
            queryUrl += "&api_key=" + apiKey;

            HttpURLConnection conn = (HttpURLConnection) new URL(queryUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", apiKey);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream response = conn.getInputStream();
                JsonNode responseNode = objectMapper.readTree(response);
                JsonNode data = responseNode.path("data");

                if (data.isArray()) {
                    for (JsonNode parkNode : data) {
                        if (parkNode.path("id").asText().equals(parkName)) {
                            Park park = objectMapper.treeToValue(parkNode, Park.class);
                            return park;
                        }
                    }
                }
                return null; // Return null if no matching ID is found

            } else {
                // Handle errors
                System.out.println("HTTP error code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Park> parseParksList(FavoritesList fL) {
        ArrayList<Park> result = new ArrayList<>();

        for (int i = 0; i < fL.size(); i++) {
            String name = fL.getFavoriteAt(i).getParkId();
            System.out.println(name);
            result.add(getParkInfo(name));
        }

        return result;
    }


    // The 'getFavorites' function will simply use the user_id stored as a cookie
    // on the web session of the user to query the database for their favorites list.
    // It uses the HttpServletRequest made by the webpage to access this cookie.
    @PostMapping("/favorites")
    public ResponseEntity<?> getFavorites(HttpServletRequest request) {
        Long user_id = null;

        double num_users = (double)(getUserCount());

        System.out.println(num_users);

        Cookie[] cookies = request.getCookies();

        //check if cookies exist.
        // If they do, attempt to find the user_id cookie.
        // If the user_id cookie exists, parse the user_id value stored.
        //

        if (cookies != null) {
            Optional<Cookie> userIdCookie = Arrays.stream(cookies).filter(
                                                cookie -> "user_id".equals(cookie.getName())
                                            )
                                            .findFirst();

            if (userIdCookie.isPresent()) { //check if user has valid session id
                try {
                    user_id = Long.parseLong(userIdCookie.get().getValue());

                }
                catch(NumberFormatException nfe) { //invalid session id
                    return ResponseEntity.badRequest().body(Map.of("message","Invalid user_id stored as cookie."));
                }
            }
        }

        if (user_id == null) { //no user id exists
            return ResponseEntity.badRequest().body(Map.of("message","No user_id stored as cookie."));
        }

        myId = user_id;

        ArrayList<Favorite> temp = favoritesRepository.findFavoritesByUserId(user_id);

        for (Favorite value : temp) {
            double num_favorites = favoritesRepository.countFavorite(value.getParkId());

            double ratio = num_favorites / num_users;

            value.setFavoriteRatio(ratio);
        }

        userFavorites = new FavoritesList();

        for (Favorite favorite : temp) {
            userFavorites.insert(favorite);
        }

        if (userFavorites.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        System.out.println(userFavorites);

        return getResponseEntity(userFavorites);

    }

    @NotNull
    private ResponseEntity<?> getResponseEntity(FavoritesList userFavorites) {
        try {
            ArrayList<Park> res = parseParksList(userFavorites);
            String jsonResponse = objectMapper.writeValueAsString(res);
            System.out.println(jsonResponse);
            return ResponseEntity.ok(jsonResponse);
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> compareFavorites() {

        if (myId == null) {
            return ResponseEntity.badRequest().body(Map.of("message","No active user!"));
        }
        else if (compareTarget == null) {
            return ResponseEntity.badRequest().body(Map.of("message","No comparison target!"));
        }


        ArrayList<Favorite> myList = favoritesRepository.findFavoritesByUserId(myId);
        ArrayList<Favorite> targetList = favoritesRepository.findFavoritesByUserId(compareTarget);

        FavoritesList union = new FavoritesList();

        for (Favorite f : myList) {
            union.insert(f);
        }
        for (Favorite f : targetList) {
            union.insert(f);
        }

        return getResponseEntity(union);

    }

}
