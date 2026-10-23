package edu.usc.csci310.project.UnitTests.ModelUnitTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.usc.csci310.project.models.*;

import java.util.ArrayList;
import java.util.List;

public class ParkUnitTests {
    private Park park;

    @Test
    public void testParkGettersAndSetters() {

        park = new Park();

        park.setId("123");
        assertEquals("123", park.getId());

        park.setUrl("https://example.com");
        assertEquals("https://example.com", park.getUrl());

        park.setFullName("Full Park Name");
        assertEquals("Full Park Name", park.getFullName());

        park.setName("Park Name");
        assertEquals("Park Name", park.getName());

        park.setParkCode("ABCD");
        assertEquals("ABCD", park.getParkCode());

        park.setDescription("Park Description");
        assertEquals("Park Description", park.getDescription());

        park.setLatitude(34.0522);
        assertEquals(34.0522, park.getLatitude());

        park.setLongitude(-118.2437);
        assertEquals(-118.2437, park.getLongitude());

        park.setLatLong("34.0522,-118.2437");
        assertEquals("34.0522,-118.2437", park.getLatLong());

        List<Activity> activitiesList = new ArrayList<>();
        park.setActivitiesList(activitiesList);
        assertEquals(activitiesList, park.getActivitiesList());

        park.setStates("CA");
        assertEquals("CA", park.getStates());

        List<EntranceFeePass> fees = new ArrayList<>();
        park.setFees(fees);
        assertEquals(fees, park.getFees());

        List<EntranceFeePass> passes = new ArrayList<>();
        park.setPasses(passes);
        assertEquals(passes, park.getPasses());

        park.setDirectionsInfo("Directions Info");
        assertEquals("Directions Info", park.getDirectionsInfo());

        park.setDirectionsUrl("https://test.com/directions");
        assertEquals("https://test.com/directions", park.getDirectionsUrl());
    }

    @Test
    public void testParkConstructor() {
        List<Activity> activitiesList = new ArrayList<>();
        List<EntranceFeePass> fees = new ArrayList<>();
        List<EntranceFeePass> passes = new ArrayList<>();

        Park park = new Park("123", "https://test.com", "Full Park Name", "Park Name",
                "TEST", "Park Description", 34.0522, -118.2437, "34.0522,-118.2437",
                activitiesList, "CA", fees, passes, "Directions Info", "https://example.com/directions");

        assertEquals("123", park.getId());
        assertEquals("https://test.com", park.getUrl());
        assertEquals("Full Park Name", park.getFullName());
        assertEquals("Park Name", park.getName());
        assertEquals("TEST", park.getParkCode());
        assertEquals("Park Description", park.getDescription());
        assertEquals(34.0522, park.getLatitude());
        assertEquals(-118.2437, park.getLongitude());
        assertEquals("34.0522,-118.2437", park.getLatLong());
        assertEquals(activitiesList, park.getActivitiesList());
        assertEquals("CA", park.getStates());
        assertEquals(fees, park.getFees());
        assertEquals(passes, park.getPasses());
        assertEquals("Directions Info", park.getDirectionsInfo());
        assertEquals("https://example.com/directions", park.getDirectionsUrl());
    }
}
