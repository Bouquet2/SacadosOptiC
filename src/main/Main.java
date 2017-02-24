package main;


import bag.Item;
import generator.ItemGenerator;


public class Main {
    /**
     * TODO: Create Glouton Algorithm
     * TODO: Generate Maximum value for a bag
     * TODO: Generate Data and compare glouton algo to maximum values
     * TODO: Generate as Html/Excel/Csv data
     */

    public static void main(String[] args) throws Exception{
        ItemGenerator generator10 = new ItemGenerator(10, 0, 10, 0, 10);
        generator10.generateItems();
        generator10.orderItems();
        System.out.println(generator10.toString());
        Item item;
        while ((item = generator10.getNextItem()) != null) {
            System.out.println(String.format("Item : %d, Ratio : %f", item.getNum(), item.getRatio()));
        }
    }
}
