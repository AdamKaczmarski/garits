package com.garits.report;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PdfFileServiceAverages {

    public static void pdfCreationAverages() {

        String filepathAverages = "./pdf_reports/Averages.pdf";


        try {
            //Creating the page
            PdfWriter stock = new PdfWriter(filepathAverages);
            PdfDocument pdfAverages = new PdfDocument(stock);
            pdfAverages.setDefaultPageSize(PageSize.A4);


            //Creating the first column
            float col = 1000f;
            float columnWidth[] = {col};
            Table name = new Table(columnWidth).setMarginTop(-20f)
                    .setTextAlignment(TextAlignment.RIGHT);


            name.addCell(new Cell().add("Team 14 - Repo").setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginBottom(30f).setMarginTop(20f).setBorderLeft(Border.NO_BORDER).setFontSize(25f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
            );
            
            float date = 250f;
            float columnDate[] = {date,date};
            Table time = new Table(columnDate).setMarginTop(10f).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginLeft(360f);
            time.addCell(new Cell().add("Date:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12));
            LocalDate now = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            time.addCell(new Cell().add(String.valueOf(now))
                    .setMarginTop(-20f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12)
            );

            float Averages = 1000f;
            float columnAverages[] = {Averages};
            Table tableAverages = new Table(columnAverages).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            tableAverages.addCell(new Cell().add("Job Type Averages Report").setBorderLeft(Border.NO_BORDER).setFontSize(12f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBold().setTextAlignment(TextAlignment.CENTER).setFontSize(30f)
            );

            float MoTdescription = 600f;
            float columnMoTDescription[] = {MoTdescription};
            Table MoTdescriptionText = new Table(columnMoTDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            MoTdescriptionText.addCell(new Cell().add("MoT Service").setFontSize(15f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
            MoTdescriptionText.addCell(new Cell().add("The average time and average cost for MoT services.").setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));

            float vehicleDescription = 600f;
            float columnvehicleDescription[] = {vehicleDescription};
            Table vehicleDescriptionText = new Table(columnvehicleDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            vehicleDescriptionText.addCell(new Cell().add("Vehicle Repairs").setFontSize(15f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
            vehicleDescriptionText.addCell(new Cell().add("The average time and average cost for vehicle repairs.").setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));

            float annualService = 600f;
            float columnAnnualService[] = {annualService};
            Table annualServiceText = new Table(columnAnnualService).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            annualServiceText.addCell(new Cell().add("Annual Services").setFontSize(15f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
            annualServiceText.addCell(new Cell().add("The average time and average cost for annual services.").setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            
            //Creating Table
            float colWidth[] = {300,300};
            Table averageCost = new Table(colWidth).setMarginTop(20f)
                    .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

            averageCost.addCell(new Cell().add("Average Time").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            averageCost.addCell(new Cell().add("Average Cost Per Job").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            averageCost.addCell(new Cell().add("Insert Value").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            averageCost.addCell(new Cell().add("Insert Value").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            
            Document document=new Document(pdfAverages);
            document.add(name);
            document.add(time);
            document.add(tableAverages);
            document.add(MoTdescriptionText);
            document.add(averageCost);
            document.add(vehicleDescriptionText);
            document.add(averageCost);
            document.add(annualServiceText);
            document.add(averageCost);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}