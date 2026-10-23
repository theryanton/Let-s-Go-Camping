package edu.usc.csci310.project.UnitTests.ModelUnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.usc.csci310.project.models.*;

public class EntranceFeePassUnitTests {

    @Test
    public void testGetCost() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        assertEquals(10.0, pass.getCost());
    }

    @Test
    public void testSetCost() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        pass.setCost(20.0);
        assertEquals(20.0, pass.getCost());
    }

    @Test
    public void testGetDescription() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        assertEquals("Annual Pass", pass.getDescription());
    }

    @Test
    public void testSetDescription() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        pass.setDescription("Senior Citizen Pass");
        assertEquals("Senior Citizen Pass", pass.getDescription());
    }

    @Test
    public void testGetTitle() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        assertEquals("Annual Pass", pass.getTitle());
    }

    @Test
    public void testSetTitle() {
        EntranceFeePass pass = new EntranceFeePass(10.0, "Annual Pass", "Annual Pass");
        pass.setTitle("Season Pass");
        assertEquals("Season Pass", pass.getTitle());
    }

}
