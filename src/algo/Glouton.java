package algo;

import bag.Bag;
import bag.Item;

import java.util.List;

/**
 * Résolution du problème du sac à dos à 2 sacs via un algorithme glouton.
 */
public class Glouton {

    public Glouton() {
    }

    public double generateOptimalValue(Bag bag1, Bag bag2, List<Item> items) {
        for (Item i : items) {
            if (bag1.addItem(i)) {
            }
            else if (bag2.addItem(i)) {
            } else {
            }
        }
        return bag1.getUtiliy() + bag2.getUtiliy();
    }
}
