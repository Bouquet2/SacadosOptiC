package algo;

import bag.Bag;
import bag.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by repocque on 24/02/2017.
 */
public class GloutonTest {

    private Glouton glouton;
    private Bag bag1;
    private Bag bag2;
    List<Item> items = new ArrayList<>();

    @BeforeMethod
    public void setUp() throws Exception {
        glouton = new Glouton();

        bag1 = new Bag(9);
        bag2 = new Bag(5);

        items.add(new Item(1, 3, 30));
        items.add(new Item(2, 4, 36));
        items.add(new Item(3, 5, 40));
        items.add(new Item(4, 2, 14));
        items.add(new Item(5, 3, 18));
        items.add(new Item(6, 1, 5));
        items.add(new Item(7, 3, 12));
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testGenerateOptimalValue() throws Exception {
        assertEquals(glouton.generateOptimalValue(bag1, bag2, items), 120.0);
    }
}