package main;


import algo.Glouton;
import algo.Relax;
import bag.Bag;
import generator.ItemGenerator;
import util.ReportGenerator;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        Random rand = new Random();
        int maxItem = 30;
        double minWeight = maxItem * (rand.nextDouble() + 1);
        double maxWeight = maxItem * 2 * (rand.nextDouble() + 1);
        double poids1 = maxItem * maxItem / (rand.nextDouble() + 2);
        double poids2 = maxItem * maxItem / (rand.nextDouble() + 2);
        double minUtil = minWeight * 2.5;
        double maxUtil = maxWeight * 2.5;
        int nbJeu = 20;

        ItemGenerator generator = new ItemGenerator(maxItem, minWeight,
                maxWeight, minUtil, maxUtil);

        Glouton glouton = new Glouton();
        Relax relax = new Relax();
        double gloutonEval;
        double relaxEval;
        Bag bag1;
        Bag bag2;
        int cpt = 0;
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.init("Evaluation de l'algorithme glouton", maxItem,
                minWeight, maxWeight, minUtil, maxUtil, nbJeu);

        while (cpt < nbJeu) {
            generator.clearAndGenerate();
            generator.orderItems();
            bag1 = new Bag(poids1);
            bag2 = new Bag(poids2);
            gloutonEval = glouton.generateOptimalValue(bag1, bag2, generator.getItems());
            bag1.clear();
            bag2.clear();
            relaxEval = relax.generateOptimalValue(bag1, bag2, generator.getItems());
            reportGenerator.setRowEval(gloutonEval, relaxEval);
            reportGenerator.setMinAndMaxGlouton(cpt, gloutonEval);
            reportGenerator.setMinAndMaxRelax(cpt, relaxEval);
            cpt++;
        }

        reportGenerator.setStats(nbJeu);
        reportGenerator.generateFile();
        reportGenerator.openFile();
    }
}
