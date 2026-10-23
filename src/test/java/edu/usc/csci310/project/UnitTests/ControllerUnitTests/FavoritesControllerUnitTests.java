package edu.usc.csci310.project.UnitTests.ControllerUnitTests;
import edu.usc.csci310.project.controllers.FavoritesController;
import edu.usc.csci310.project.repositories.FavoritesRepository;
import edu.usc.csci310.project.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.usc.csci310.project.models.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@WebMvcTest(FavoritesController.class)
public class FavoritesControllerUnitTests {
    @Autowired
    private FavoritesController favoritesController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private FavoritesRepository favoritesRepository;

    @BeforeEach
    public void setup() {

    }

//    @Test
//    public void testGetCount() {
//        when(userRepository.count()).thenReturn(1L);
//        assertEquals(1L, favoritesController.getUserCount());
//    }

//    @Test
//    public void testNoUserLogin() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.getCookies()).thenReturn(null);
//
//        ResponseEntity<?> response = favoritesController.getFavorites(request);
//
//        try {
//            assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
//            assertEquals("{message=No user_id stored as cookie.}", response.getBody().toString());
//        }
//        catch(NullPointerException npe) {
//            fail();
//        }
//    }

//    @Test
//    public void testInvalidUserIdCookie() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        Cookie invalidCookie = new Cookie("user_id", "invalid");
//        when(request.getCookies()).thenReturn(new Cookie[]{invalidCookie});
//
//        ResponseEntity<?> response = favoritesController.getFavorites(request);
//
//        try {
//            assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
//            assertEquals("{message=Invalid user_id stored as cookie.}", response.getBody().toString());
//        }
//        catch(NullPointerException npe) {
//            fail();
//        }
//    }

//    @Test
//    public void testNoUserIdCookiePresent() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        Cookie invalidCookie = new Cookie("test", "invalid");
//        when(request.getCookies()).thenReturn(new Cookie[]{invalidCookie});
//
//        ResponseEntity<?> response = favoritesController.getFavorites(request);
//
//        try {
//            assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
//            assertEquals("{message=No user_id stored as cookie.}", response.getBody().toString());
//        }
//        catch(NullPointerException npe) {
//            fail();
//        }
//    }

//    @Test
//    public void testGetEmptyFavorites(){
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        Cookie validCookie = new Cookie("user_id", "1");
//        when(request.getCookies()).thenReturn(new Cookie[]{validCookie});
//
//        ArrayList<Favorite> favorites = new ArrayList<>();
//        when(favoritesRepository.findFavoritesByUserId(1L)).thenReturn(favorites);
//
//        when(favoritesRepository.countFavorite("park1")).thenReturn(10L);
//        when(favoritesRepository.countFavorite("park2")).thenReturn(20L);
//
//        ResponseEntity<?> response = favoritesController.getFavorites(request);
//        try {
//            assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
//        }
//        catch(NullPointerException npe) {
//            fail();
//        }
//    }

//    @Test
//    public void testGetFavoritesSuccess(){
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        Cookie validCookie = new Cookie("user_id", "1");
//        when(request.getCookies()).thenReturn(new Cookie[]{validCookie});
//
//        Favorite favorite1 = new Favorite();
//        favorite1.setParkId("park1");
//        Favorite favorite2 = new Favorite();
//        favorite2.setParkId("park2");
//        ArrayList<Favorite> favorites = new ArrayList<>(Arrays.asList(favorite1, favorite2));
//        when(favoritesRepository.countFavorite("park2")).thenReturn(30L);
//        when(favoritesRepository.countFavorite("park1")).thenReturn(20L);
//        when(favoritesRepository.findFavoritesByUserId(1L)).thenReturn(favorites);
//
//
//        ResponseEntity<?> response = favoritesController.getFavorites(request);
//        try {
//            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
//            assertEquals("[null,null]", response.getBody().toString());
//        }
//        catch(NullPointerException npe) {
//            fail();
//        }
//    }

}
