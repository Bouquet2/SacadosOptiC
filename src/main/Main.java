package main;


import algo.Glouton;
import algo.Relax;
import bag.Bag;
import generator.ItemGenerator;
import util.ReportGenerator;

public class Main {
    //TODO Générer un tableau

    public static void main(String[] args) throws Exception {
        ItemGenerator generator = new ItemGenerator(10, 0, 10, 5, 10);
        int nbJeu = 20;

        Glouton glouton = new Glouton();
        Relax relax = new Relax();
        double gloutonEval;
        double relaxEval;
        Bag bag1;
        Bag bag2;
        int cpt = 0;
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.init("Evaluation de l'algorithme glouton", 10, 0, 10, 5, 10, nbJeu);

        while (cpt < nbJeu) {
            generator.clearAndGenerate();
            generator.orderItems();
            bag1 = new Bag(20);
            bag2 = new Bag(5);
            gloutonEval = glouton.generateOptimalValue(bag1, bag2, generator.getItems());
            bag1.clear();
            bag2.clear();
            relaxEval   = relax.generateOptimalValue(bag1, bag2, generator.getItems());
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
