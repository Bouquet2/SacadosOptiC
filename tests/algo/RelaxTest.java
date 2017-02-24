package algo;

import bag.Bag;
import bag.Item;
import generator.ItemGenerator;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class RelaxTest {
    private Relax algoRelax;
    private List<Item> items;
    private List<Item> items2;
    private Bag bag1;
    private Bag bag2;
    private Bag bag3;
    private Bag bag4;

    @Test
    public void testGenerateOptimalValue() throws Exception {
        algoRelax = new Relax();
        bag1 = new Bag(10);
        bag2 = new Bag(2);
        bag3 = new Bag(9);
        bag4 = new Bag(5);

        items = new ArrayList<>();
        items.add(new Item(1, 3, 30));
        items.add(new Item(2, 4, 36));
        items.add(new Item(3, 2, 16));
        items.add(new Item(4, 4, 28));
        items.add(new Item(5, 3, 18));
        items.add(new Item(6, 1, 5));
        items.add(new Item(7, 4, 16));

        items2 = new ArrayList<>();
        items2.add(new Item(1, 3, 30));
        items2.add(new Item(2, 4, 36));
        items2.add(new Item(3, 5, 40));
        items2.add(new Item(4, 2, 14));
        items2.add(new Item(5, 3, 18));
        items2.add(new Item(6, 1, 5));
        items2.add(new Item(7, 3, 12));


    }

    @Test
    public void testRelaxDefaultItems() throws Exception {
        assertEquals(algoRelax.generateOptimalValue(bag1, bag2, items), 103.0);
        assertEquals(algoRelax.generateOptimalValue(bag3, bag4, items2), 120.0);
    }

}