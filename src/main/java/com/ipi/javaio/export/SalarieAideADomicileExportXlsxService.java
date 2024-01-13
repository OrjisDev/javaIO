package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Service
public class SalarieAideADomicileExportXlsxService {

    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public static final int COL_NOM = 0;
    public static final int COL_DEBUT_CONTRAT = 1;
    public static final int COL_CONGES_PAYES_ACQUIS = 2;
    public static final int COL_JOURS_TRAVAILLES = 3;
    public static final int COL_DROIT_CONGES_PAYES = 4;

    public SalarieAideADomicileExportXlsxService(SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(ServletOutputStream outputStream) throws IOException{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Salaries");
        Row headerRow = sheet.createRow(0);


        CellStyle styleHeader = styleColor(workbook);

        Cell cellHeaderPremierDuMois = headerRow.createCell(COL_NOM);
        cellHeaderPremierDuMois.setCellValue("Nom");
        cellHeaderPremierDuMois.setCellStyle(styleHeader);

        Cell cellHeaderIdSalarie = headerRow.createCell(COL_DEBUT_CONTRAT);
        cellHeaderIdSalarie.setCellValue("Debut Du Contrat");
        cellHeaderIdSalarie.setCellStyle(styleHeader);

        Cell cellHeaderNom = headerRow.createCell(COL_CONGES_PAYES_ACQUIS);
        cellHeaderNom.setCellValue("Conges Payes Aquis");
        cellHeaderNom.setCellStyle(styleHeader);

        Cell cellHeaderJourTravailles = headerRow.createCell(COL_JOURS_TRAVAILLES);
        cellHeaderJourTravailles.setCellValue("Jour Travailles");
        cellHeaderJourTravailles.setCellStyle(styleHeader);

        Cell cellHeaderCongesPayesAcquis = headerRow.createCell(COL_DROIT_CONGES_PAYES);
        cellHeaderCongesPayesAcquis.setCellValue("Droit conges payes");
        cellHeaderCongesPayesAcquis.setCellStyle(styleHeader);

        CellStyle cellStyleBorder = newStyleBorder(workbook);

        Iterable<SalarieAideADomicile> allSalaries = salarieAideADomicileService.getSalaries();
        int rowIndex = 1;
        int cellIndex = 0;
        for (SalarieAideADomicile salarie : allSalaries) {
            Row row = sheet.createRow(rowIndex++);

            Cell cellPremierDuMois = row.createCell(COL_NOM);
            cellPremierDuMois.setCellValue(salarie.getNom());
            Cell cellIdSalarie = row.createCell(COL_DEBUT_CONTRAT);
            cellIdSalarie.setCellValue(salarie.getMoisDebutContrat().toString());
            Cell cellNom = row.createCell(COL_CONGES_PAYES_ACQUIS);
            cellNom.setCellValue(salarie.getCongesPayesAcquisAnneeN());
            Cell cellJoursTravailles = row.createCell(COL_JOURS_TRAVAILLES);
            cellJoursTravailles.setCellValue(salarie.getJoursTravaillesAnneeN());
            Cell cellCongesPayesAcquis = row.createCell(COL_DROIT_CONGES_PAYES);
            cellCongesPayesAcquis.setCellValue(salarie.aLegalementDroitADesCongesPayes());
        }

        workbook.write(outputStream);
        workbook.close();

    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle newStyleBorder(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);
        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }
}

