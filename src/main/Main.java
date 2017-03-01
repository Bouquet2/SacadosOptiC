package main;


import algo.Glouton;
import algo.Relax;
import bag.Bag;
import generator.ItemGenerator;
import util.ReportGenerator;

public class Main {
    //TODO Indiqué sur le tableau excel le min poids et max poids
    //TODO Indiqué sur le tableau excel le min utilité et max utilité
    //TODO Pour chaque évaluation insérer une ligne dans le tableau
    //TODO Générer le tableau

    public static void main(String[] args) throws Exception {
        ItemGenerator generator = new ItemGenerator(10, 0, 10, 5, 10);

        Glouton glouton = new Glouton();
        Relax relax = new Relax();
        double gloutonEval;
        double relaxEval;
        Bag bag1;
        Bag bag2;
        int cpt = 0;
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.init("TEST CECI EST UN TEST. 1 2 TEST 2 1");

        while (cpt < 10) {
            generator.clearAndGenerate();
            generator.orderItems();
            bag1 = new Bag(20);
            bag2 = new Bag(5);
            gloutonEval = glouton.generateOptimalValue(new Bag(800), new Bag(400), generator.getItems());
            bag1.clear();
            bag2.clear();
            relaxEval   = relax.generateOptimalValue(bag1, bag2, generator.getItems());
            cpt++;
            System.out.println(String.format("Generation %d", cpt));
            System.out.println(String.format("Glouton evaluation : %f", gloutonEval));
            System.out.println(String.format("Relax evaluation : %f", relaxEval));
            System.out.println(String.format("Ratio : %f", relaxEval / gloutonEval));
            System.out.println("\n");
            reportGenerator.setRowEval(gloutonEval, relaxEval);
        }

        reportGenerator.generateFile();
        reportGenerator.openFile();
    }
}
