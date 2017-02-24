package bag;


public class Item {
    private int num;
    private double weight;
    private double util;
    private double ratio;

    public Item(int num, double weight, double util) throws Exception {
        if (num <= 0 || weight <= 0 || util <= 0)
            throw new Exception(String.format(
                    "Item number, weight and utility can't have negative values. num: %s, weight: %s, util: %s",
                    num, weight, util));
        this.num = num;
        this.weight = weight;
        this.util = util;
        this.ratio = util / weight;
    }

    public double getRatio() {
        return ratio;
    }

    public double getWeight() {
        return weight;
    }

    public double getUtil() {
        return util;
    }

    public int getNum() {
        return num;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Item : ");
        sb.append(num).append("\t").append("w : ").append(weight);
        sb.append(" u : ").append(util);
        return sb.toString();
    }
}
