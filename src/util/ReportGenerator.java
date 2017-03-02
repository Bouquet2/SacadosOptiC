package util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    private double maxRapport;
    private double minRapport;
    private double moyRapport;

    public ReportGenerator() {
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet("FirstSheet");

        //data are written from the row 5
        currentRow = 5;

        //Style for percentage format
        stylePercentage = workbook.createCellStyle();
        stylePercentage.setDataFormat(workbook.createDataFormat().getFormat("0.000%"));

        //On crée queqlues soit le nombre de données au moins 10 lignes pour afficher les stats
        for(int i = 0; i < 10; i++) {
            sheet.createRow(i);
        }
        maxRapport = 0;
        minRapport = 1;
    }

    public void setRowEval(double gloutonEval, double relaxEval) {
        double rapportEval = gloutonEval / relaxEval;
        currentRow++;
        HSSFRow rowhead = sheet.createRow(currentRow);
        if(rapportEval > maxRapport) {
            maxRapport = rapportEval;
        }
        if(rapportEval < minRapport) {
            minRapport = rapportEval;
        }
        moyRapport += rapportEval;
        rowhead.createCell(0).setCellValue(String.format("Jeu %d", currentRow - 5));
        rowhead.createCell(1).setCellValue(gloutonEval);
        rowhead.createCell(2).setCellValue(relaxEval);
        rowhead.createCell(3).setCellValue(rapportEval);
        rowhead.createCell(4).setCellStyle(stylePercentage);
        rowhead.getCell(4).setCellValue(rapportEval);
    }

    private void setTitle(String title) {
        sheet.getRow(0).createCell(5).setCellValue(title);
    }

    public void init(String title, int maxItem, double minWeight, double maxWeight,
                     double minUtil, double maxUtil, int nbJeu) {
        setTitle(title);
        int rowInfos = 2;
        sheet.getRow(rowInfos).createCell(1).setCellValue("Nombre d'items par jeu : ");
        sheet.getRow(rowInfos).createCell(2).setCellValue(maxItem);
        sheet.getRow(rowInfos).createCell(3).setCellValue("Poids minimal : ");
        sheet.getRow(rowInfos).createCell(4).setCellValue(minWeight);
        sheet.getRow(rowInfos).createCell(5).setCellValue("Poids maximal : ");
        sheet.getRow(rowInfos).createCell(6).setCellValue(maxWeight);
        sheet.getRow(rowInfos).createCell(7).setCellValue("Utilité minimale : ");
        sheet.getRow(rowInfos).createCell(8).setCellValue(minUtil);
        sheet.getRow(rowInfos).createCell(9).setCellValue("Utilité maximale : ");
        sheet.getRow(rowInfos).createCell(10).setCellValue(maxUtil);
        sheet.getRow(rowInfos).createCell(11).setCellValue("Nombre de jeux : ");
        sheet.getRow(rowInfos).createCell(12).setCellValue(nbJeu);
        int rowTitles = 4;
        sheet.getRow(rowTitles).createCell(1).setCellValue("Evaluation glouton");
        sheet.getRow(rowTitles).createCell(2).setCellValue("Evaluation relaxée");
        sheet.getRow(rowTitles).createCell(3).setCellValue("Rapport");
        sheet.getRow(rowTitles).createCell(4).setCellValue("Pourcentage");
        sheet.getRow(rowTitles).createCell(6).setCellValue("Min evaluation gloutonne");
        sheet.getRow(rowTitles).createCell(7).setCellValue("Max evaluation gloutonne");
        sheet.getRow(rowTitles).createCell(8).setCellValue("Min evaluation relaxée");
        sheet.getRow(rowTitles).createCell(9).setCellValue("Max evaluation relaxée");
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

    //Permet de se faire une idée de l'écart entre les jeux de données
    public void setMinAndMaxGlouton(int cpt, double gloutonEval) {
        if(cpt == 0) {
            minEvalGlouton = gloutonEval;
            maxEvalGlouton = gloutonEval;
        }
        if(gloutonEval >= maxEvalGlouton) {
            maxEvalGlouton = gloutonEval;
        }
        if(gloutonEval <= minEvalGlouton) {
            minEvalGlouton = gloutonEval;
        }
    }

    //Permet de se faire une idée de l'écart entre les jeux de données
    public void setMinAndMaxRelax(int cpt, double relaxEval) {
        if(cpt == 0) {
            minEvalRelax = relaxEval;
            maxEvalRelax = relaxEval;
        }
        if(relaxEval >= maxEvalRelax) {
            maxEvalRelax = relaxEval;
        }
        if(relaxEval <= minEvalRelax) {
            minEvalRelax = relaxEval;
        }
    }

    public void setStats(int nbJeu) {
        HSSFRow rowheadMinMax = sheet.createRow(5);

        //Min et max évaluations gloutonne
        rowheadMinMax.createCell(7).setCellValue(maxEvalGlouton);
        rowheadMinMax.createCell(6).setCellValue(minEvalGlouton);

        //Min et max évaluations relaxée
        rowheadMinMax.createCell(9).setCellValue(maxEvalRelax);
        rowheadMinMax.createCell(8).setCellValue(minEvalRelax);

        //Min et max rapport entre evaluation gloutonne et relaxée
        sheet.getRow(7).createCell(6).setCellValue("Meilleur rapport");
        sheet.getRow(8).createCell(6).setCellValue(maxRapport);

        sheet.getRow(7).createCell(7).setCellValue("Plus mauvais rapport");
        sheet.getRow(8).createCell(7).setCellValue(minRapport);

        //Moyenne des rapports
        sheet.getRow(7).createCell(8).setCellValue("Moyenne des rapports");
        sheet.getRow(8).createCell(8).setCellValue(moyRapport/nbJeu);

        for(int i = 0; i <= 12; i++) {
            sheet.autoSizeColumn(i);
        }

    }
}
