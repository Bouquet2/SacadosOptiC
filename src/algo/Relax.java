package algo;

import bag.Bag;
import bag.Item;

import java.util.List;

/**
 * Donne la solution optimale relaxée pour un problème de sac à dos à 1 ou n sacs.
 */
public class Relax {

    public Relax() {
    }

    public double generateOptimalValue(Bag bag1, Bag bag2, List<Item> items) throws Exception {
        if (bag1 == null || bag2 == null)
            throw new Exception("Bags can't be null");

        Bag bag = new Bag(bag1.getMaxWeight() + bag2.getMaxWeight());
        for (Item item : items) {
            if (!bag.addItem(item)) {
                if (!bag.isFull()) {
                    double partialItemWeight = bag.getMaxWeight() - bag.getWeight();
                    Item partialItem = new Item(item.getNum(), partialItemWeight,
                            partialItemWeight / item.getWeight() * item.getUtil());
                    bag.addItem(partialItem);
                    break;
                }
            }
        }
        return bag.getUtiliy();
    }
}
