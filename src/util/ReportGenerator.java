package util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//IMPORTANT

//Excel
//TODO Mettre en valeur le rapport le plus optimale et le plus décevant et la moyenne et la médian et l'écart type...
//TODO Trouver une solution pour le jar !! (BUILDER)

//Rapport latex
//TODO Quelles valeurs générer ?
//TODO Pourquoi celles ci ?
//TODO Explication des données obtenues

//EN OPTION
//TODO Formater toutes les cellules (réduire le nombre de chiffre après la virgule)
//TODO Formater la cellule rapport (afficher un %)
//TODO Afficher un VRAI tableau
//TODO Mettre en plus grande police le titre
//TODO Changer la taille des cellules pour générer un "beau" tableau
//TODO Ouvrir le fichier généré automatiquement


public class ReportGenerator {

    private String filename = "val_remy_evaluation_algo_glouton.xls";
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private int currentRow;
    private CellStyle stylePercentage;
    private double minEvalGlouton;
    private double minEvalRelax;
    private double maxEvalGlouton;
    private double maxEvalRelax;
    private HSSFRow rowheadMinMax;

    public ReportGenerator() {
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet("FirstSheet");
        currentRow = 1;
        stylePercentage = workbook.createCellStyle();
        stylePercentage.setDataFormat(workbook.createDataFormat().getFormat("0.000%"));
        rowheadMinMax = sheet.createRow(3);
    }

    public void setRowEval(double gloutonEval, double relaxEval) {
        double rapportEval = gloutonEval / relaxEval;
        currentRow++;
        HSSFRow rowhead = sheet.createRow(currentRow);
        rowhead.createCell(0).setCellValue(String.format("Jeu %d", currentRow - 2));
        rowhead.createCell(1).setCellValue(gloutonEval);
        rowhead.createCell(2).setCellValue(relaxEval);
        rowhead.createCell(3).setCellValue(rapportEval);
        rowhead.createCell(4).setCellStyle(stylePercentage);
        rowhead.getCell(4).setCellValue(rapportEval);
    }

    private void setTitle(String title) {
        HSSFRow rowhead = sheet.createRow(0); //cast short si il faut
        rowhead.createCell(5).setCellValue(title);
        currentRow++;
    }

    public void init(String title) {
        setTitle(title);
        HSSFRow rowhead = sheet.createRow(currentRow);
        currentRow++;
        rowhead.createCell(1).setCellValue("Evaluation glouton");
        rowhead.createCell(2).setCellValue("Evaluation relaxée");
        rowhead.createCell(3).setCellValue("Rapport");
        rowhead.createCell(4).setCellValue("Pourcentage");
        rowhead.createCell(6).setCellValue("Min evaluation gloutonne");
        rowhead.createCell(7).setCellValue("Max evaluation gloutonne");
        rowhead.createCell(8).setCellValue("Min evaluation relaxée");
        rowhead.createCell(9).setCellValue("Max evaluation relaxée");
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void generateFile() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        workbook.write(fileOut);
        fileOut.close();
        System.out.println(String.format("%s fichier généré", this.filename));
    }

    public void openFile() throws IOException {
        Desktop.getDesktop().open(new File(filename));
    }

    public void setMinAndMaxGlouton(int cpt, double gloutonEval) {
        if(cpt == 0) {
            minEvalGlouton = gloutonEval;
            maxEvalGlouton = gloutonEval;
        }
        if(gloutonEval >= maxEvalGlouton) {
            maxEvalGlouton = gloutonEval;
            rowheadMinMax.createCell(7).setCellValue(maxEvalGlouton);
        }
        if(gloutonEval <= minEvalGlouton) {
            minEvalGlouton = gloutonEval;
            rowheadMinMax.createCell(6).setCellValue(minEvalGlouton);
        }
    }

    public void setMinAndMaxRelax(int cpt, double relaxEval) {
        if(cpt == 0) {
            minEvalRelax = relaxEval;
            maxEvalRelax = relaxEval;
        }
        if(relaxEval >= maxEvalRelax) {
            maxEvalRelax = relaxEval;
            rowheadMinMax.createCell(9).setCellValue(maxEvalRelax);
        }
        if(relaxEval <= minEvalRelax) {
            minEvalRelax = relaxEval;
            rowheadMinMax.createCell(8).setCellValue(minEvalRelax);
        }
    }
}
