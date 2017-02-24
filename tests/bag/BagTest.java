package bag;

import generator.ItemGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class BagTest {
    private ItemGenerator generator;
    private Bag bag;

    @BeforeMethod
    public void setUp() throws Exception {
        generator = new ItemGenerator(10, 0, 10, 0, 10);
        generator.generateItems();
        bag = new Bag(50);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        generator = null;
        bag = null;
    }

    @Test
    public void testAddItem() throws Exception {
        assertEquals(bag.getMaxWeight(), 50.0);
        assertEquals(bag.addItem(generator.getNextItem()), true);
        Item overweightItem = new Item(2, 50, 10);
        assertEquals(bag.addItem(overweightItem), false);
        assertEquals(bag.addItem(new Item(2, 2, 10)), true);
        assertEquals(bag.addItem(generator.getNextItem()), false);
        assertEquals(bag.getNbItem(), 2);
    }

    @Test(expectedExceptions = Exception.class)
    public void testGenerateBagException() throws Exception {
        new Bag(-10);
    }

}