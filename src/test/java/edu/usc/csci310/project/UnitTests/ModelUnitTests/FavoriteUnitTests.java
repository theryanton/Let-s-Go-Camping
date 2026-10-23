package edu.usc.csci310.project.UnitTests.ModelUnitTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.usc.csci310.project.models.*;

public class FavoriteUnitTests {
    private Favorite a;
    private Favorite b;

    @BeforeEach
    public void setUp() {
        a = new Favorite();
        a.setUser_id(1L);
        a.setFavoriteRatio(10.0);
        a.setParkId("park");

        b = new Favorite();
        b.setUser_id(2L);
        b.setFavoriteRatio(5.0);
        b.setParkId("bark");
    }

    @Test
    public void testFavoriteConstructor() {
        Favorite c = new Favorite(1L,"dark");
        assertEquals(1L,c.getUser_id());
        assertEquals("dark",c.getParkId());

    }

    @Test
    public void testGetSetFavoriteRatio() {
        assertEquals(10.0, a.getFavoriteRatio(), 0.001);
        a.setFavoriteRatio(20.0);
        assertEquals(20.0, a.getFavoriteRatio(), 0.001);
    }

    @Test
    public void testGetSetUserId() {
        assertEquals(1L, a.getUser_id());
        a.setUser_id(3L);
        assertEquals(3L, a.getUser_id());
    }

    @Test
    public void testGetSetFavoriteId() {
        assertEquals("park", a.getParkId());
        a.setParkId("mark");
        assertEquals("mark", a.getParkId());
    }

    @Test
    public void testCompareTo() {
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
        a.setFavoriteRatio(5.0);
        assertEquals(0, a.compareTo(b));
    }

}
