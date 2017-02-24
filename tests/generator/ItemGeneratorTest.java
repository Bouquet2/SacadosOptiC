package generator;

import bag.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ItemGeneratorTest {
    private ItemGenerator generator10;

    @BeforeMethod
    public void setUp() throws Exception {
        generator10 = new ItemGenerator(10, 0, 10, 0, 10);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        generator10 = null;
    }

    @DataProvider
    public Object[][] getExceptionItemGenerators() {
        return new Object[][]{
                {10, 5, 3, 10, 15},
                {-5, 0, 1, 0, 1},
                {5, -5, 1, 0, 10},
                {3, 1, 2, -2, -1},
                {0, 0, 0, 0, 0},
                {10, 1, 5, 10, 5}
        };
    }
    //maxUtil < minUtil

    @Test(dataProvider = "getExceptionItemGenerators", expectedExceptions = Exception.class)
    public void testItemGeneratorException(int maxItem, double minWeight, double maxWeight,
                                           double minUtil, double maxUtil) throws Exception {
        new ItemGenerator(maxItem, minWeight, maxWeight, minUtil, maxUtil);
    }

    @DataProvider
    public Object[][] getItemGenerators() {
        return new Object[][]{
                {10, 0, 10, 0, 10},
                {5, 50, 51, 20, 35}
        };
    }

    @Test(dataProvider = "getItemGenerators")
    public void testConstructorItemGenerator(int maxItem, double minWeight, double maxWeight,
                                             double minUtil, double maxUtil) throws Exception {
        new ItemGenerator(maxItem, minWeight, maxWeight, minUtil, maxUtil);
    }

    @Test
    public void testGenerateItems() throws Exception {
        assertEquals(generator10.getMaxItem(), 10);
        assertEquals(generator10.getNbItem(), 0);
        generator10.generateItems();
        assertEquals(generator10.getMaxItem(), 10);
        assertEquals(generator10.getNbItem(), 10);

        //Verify that items are between 0 and 10 in utility and weight
        Item item;
        while ((item = generator10.getNextItem()) != null) {
            assertTrue(item.getUtil() <= 10);
            assertTrue(item.getUtil() >= 0);
            assertTrue(item.getWeight() <= 10);
            assertTrue(item.getWeight() >= 0);
        }
    }

    @Test
    public void testGetItems() throws Exception {
        generator10.generateItems();
        assertEquals(10, generator10.getNbItem());
        Item item;
        int cpt = 0;
        while ((item = generator10.getNextItem()) != null) {
            cpt++;
            assertEquals(item.getNum(), cpt);
        }
        assertEquals(cpt, generator10.getNbItem());
    }

    @Test
    public void testOrderItems() throws Exception {
        ItemGenerator itemGenerator = new ItemGenerator(10, 0, 10, 0, 10);
        itemGenerator.generateItems();
        itemGenerator.orderItems();
        Item i1 = itemGenerator.getNextItem();
        Item i2;
        while( (i2 = itemGenerator.getNextItem()) != null) {
            assertTrue(i1.getRatio() > i2.getRatio());
            i1 = i2;
        }
    }
}