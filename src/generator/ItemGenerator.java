package generator;

import bag.Item;
import java.util.*;


public class ItemGenerator {
    private int currentPosition;
    private int maxItem;
    private double minWeight;
    private double maxWeight;
    private double minUtil;
    private double maxUtil;
    private List<Item> items;
    private Random rand;

    public ItemGenerator(int maxItem, double minWeight, double maxWeight,
                         double minUtil, double maxUtil) throws Exception {
        if (maxWeight < minWeight)
            throw new Exception(
                    String.format("Maximum weight is inferior to minimum weight. Min %s Max %s",
                            minWeight, maxWeight));
        if (maxUtil < minUtil)
            throw new Exception(String.format("Maximum utility is inferior to minimum utility. Min %s Max %s",
                    minUtil, maxUtil));
        if (maxItem <= 0 || minWeight < 0 || minUtil < 0)
            throw new Exception(String.format("Min/Max item, utility or weight is negative. MaxItem %s MinWeight %s minUtil %s",
                    maxItem, minWeight, minUtil));
        this.maxItem = maxItem;
        this.currentPosition = -1;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.minUtil = minUtil;
        this.maxUtil = maxUtil;
        this.items = new ArrayList<>();
        this.rand = new Random();
    }

    public Item getNextItem() {
        if (currentPosition + 1 >= items.size())
            return null;
        currentPosition++;
        return items.get(currentPosition);
    }

    public void resetPosition() {
        this.currentPosition = 0;
    }

    public void clearItems() {
        items.clear();
    }

    public void clearAndGenerate() throws Exception {
        items.clear();
        generateItems();
    }

    public void generateItems() throws Exception {
        while (!isFull())
            generateItem();
    }

    private void generateItem() throws Exception {
        items.add(new Item(items.size() + 1, randWeigh(), randUtil()));
    }

    private double randWeigh() {
        return rand.nextDouble() * (maxWeight - minWeight) + minWeight;
    }

    private double randUtil() {
        return rand.nextDouble() * (maxUtil - minUtil) + minUtil;
    }

    private boolean isFull() {
        return maxItem <= items.size();
    }

    public int getNbItem() {
        return items.size();
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void orderItems() {
        items.sort((i1, i2) -> i1.getRatio() < i2.getRatio() ? 1 : -1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ItemGenerator \n");
        sb.append("Items : ").append(maxItem).append("\n");
        sb.append("Min weight : ").append(minWeight).append(" Max weight : ").append(maxWeight).append("\n");
        sb.append("Min utility : ").append(minUtil).append(" Max utility : ").append(maxUtil).append("\n");
        for (Item item : items)
            sb.append(item.toString()).append("\n");
        return sb.toString();
    }
}
