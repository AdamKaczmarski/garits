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
public class PdfFileServiceLedger {

    public static void pdfCreationLedger() {
        String filepathStock = "C:\\Users\\adelz\\OneDrive - City, University of London\\Desktop\\PdfFiles\\garitsStockLedger.pdf";

        try {
            //Creating the page
            PdfWriter stock = new PdfWriter(filepathStock);
            PdfDocument pdfStock = new PdfDocument(stock);
            pdfStock.setDefaultPageSize(PageSize.A4.rotate());


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

            //Creating Table
            float colWidth[] = {115, 115, 115, 115, 115, 115, 115};
            Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                    .setMarginRight(-20f).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            information.addCell(new Cell().add("Part Name").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Code").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Manufacturer").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Vehicle Type").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Year(s)").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Price").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Stock Level").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );


            Document document=new Document(pdfStock);
            document.add(name);
            document.add(time);
            document.add(information);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
