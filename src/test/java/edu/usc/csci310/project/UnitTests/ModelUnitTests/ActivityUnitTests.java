package edu.usc.csci310.project.UnitTests.ModelUnitTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.usc.csci310.project.models.*;

public class ActivityUnitTests {

    @Test
    public void testGetId() {
        Activity activity = new Activity("1", "Running");
        assertEquals("1", activity.getId());
    }

    @Test
    public void testSetId() {
        Activity activity = new Activity("1", "Hiking");
        activity.setId("2");
        assertEquals("2", activity.getId());
    }

    @Test
    public void testGetName() {
        Activity activity = new Activity("1", "Running");
        assertEquals("Running", activity.getName());
    }

    @Test
    public void testSetName() {
        Activity activity = new Activity("1", "Running");
        activity.setName("Swimming");
        assertEquals("Swimming", activity.getName());
    }

}
