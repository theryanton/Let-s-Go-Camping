package edu.usc.csci310.project.UnitTests.ModelUnitTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.usc.csci310.project.models.*;

public class FavoritesUnitTests {

    //----------------------------------------------
    // UNIT TESTS FOR FAVORITES LIST with PARKLIST |
    //----------------------------------------------


    private FavoritesList favoritesList;
    private Favorite park1;
    private Favorite park2;

    @BeforeEach
    void setUp() {
        favoritesList = new FavoritesList();
        park1 = new Favorite();
        park2 = new Favorite();
    }

    @Test
    void testEmptyWhenEmpty() {
        assertTrue(favoritesList.isEmpty());
    }

    @Test
    void testEmptyWhenNotEmpty() {
        favoritesList.insert(park1);

        assertFalse(favoritesList.isEmpty());
    }

    @Test
    void testInsertAndFind() {
        favoritesList.insert(park1);
        favoritesList.insert(park2);

        assertEquals(0, favoritesList.find(park1));
        assertEquals(1, favoritesList.find(park2));
    }

    @Test
    void testGetParkAt() {
        favoritesList.insert(park1);
        favoritesList.insert(park2);

        assertSame(park1, favoritesList.getFavoriteAt(0));
        assertSame(park2, favoritesList.getFavoriteAt(1));
    }

    @Test
    void testFavoritesListRemove() {
        favoritesList.insert(park1);
        favoritesList.insert(park2);

        favoritesList.remove(0);
        assertEquals(-1, favoritesList.find(park1));
        assertEquals(0, favoritesList.find(park2));
    }

    @Test
    void testRemoveNonExistentElement() {
        favoritesList.insert(park1);
        try {
            favoritesList.remove(1); // Attempt to remove an index that doesn't exist
        }
        catch(IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds for length 1",e.getMessage());
            assertEquals(1, favoritesList.size()); // Size should remain unchanged
        }
    }

    @Test
    void testFindNonExistentPark() {
        favoritesList.insert(park1);
        assertEquals(-1, favoritesList.find(park2)); // park2 was never inserted
    }

    @Test
    void testInsertDuplicateParks() {
        favoritesList.insert(park1);
        favoritesList.insert(park1); // Attempt to insert duplicate
        assertEquals(2, favoritesList.size()); // Size should increase, assuming duplicates are allowed
    }

    @Test
    void testRemoveAll() {
        favoritesList.insert(park1);
        favoritesList.insert(park2);
        favoritesList.remove(0);
        favoritesList.remove(0); // Remove all elements
        assertEquals(0, favoritesList.size());
    }

    @Test
    void testEmptyListFind() {
        assertEquals(-1, favoritesList.find(park1)); // Attempt to find in an empty list
    }

    @Test
    void testToString() {
        park1.setParkId("ABCD");
        park1.setFavoriteRatio(0.5);
        park2.setParkId("EFGH");
        park2.setFavoriteRatio(0.7);

        favoritesList.insert(park1);
        favoritesList.insert(park2);
        assertEquals("EFGH,ABCD,", favoritesList.toString());
    }

}
