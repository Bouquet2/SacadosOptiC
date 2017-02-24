package bag;

import org.testng.annotations.Test;


public class ItemTest {

    @Test(expectedExceptions = Exception.class)
    public void testItemException1() throws Exception {
        Item item = new Item(15, -1, 3);
    }

    @Test(expectedExceptions = Exception.class)
    public void testItemException2() throws Exception {
        Item item = new Item(-1, 2, 3);
    }

    @Test(expectedExceptions = Exception.class)
    public void testItemException3() throws Exception {
        Item item = new Item(1, 2, 0);
    }

    @Test(expectedExceptions = Exception.class)
    public void testItemException4() throws Exception {
        Item item = new Item(0, 0, 0);
    }

}