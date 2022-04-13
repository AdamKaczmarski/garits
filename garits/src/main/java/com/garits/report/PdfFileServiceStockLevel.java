package com.garits.report;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import javax.lang.model.element.Element;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class PdfFileServiceStockLevel {
    public static void pdfCreation()
    {
        String filepath="./pdf_reports/Stock_Level.pdf";

        try {
            //Creating the page
            PdfWriter writer=new PdfWriter(filepath);
            PdfDocument pdfdoc=new PdfDocument(writer);
            pdfdoc.setDefaultPageSize(PageSize.A4.rotate());

            //Creating the first column
            float col = 800f;
            float columnWidth[] = {col};
            Table name = new Table(columnWidth).setMarginTop(-20f)
                    .setTextAlignment(TextAlignment.RIGHT);


            name.addCell(new Cell().add("Team 14 - Repo").setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginBottom(30f).setMarginTop(20f).setBorderLeft(Border.NO_BORDER).setFontSize(25f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setMarginLeft(10f)
            );

            float address = 80f;
            float columnAddress[] = {address,address,address,address};
            Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                    .setMarginRight(30f)
                    ;

            location.addCell(new Cell(0,4).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(10));
            location.addCell(new Cell(0,4).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(10));
            location.addCell(new Cell(0,4).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(10));
            location.addCell(new Cell(0,4).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(10)

            );

            float title = 800f;
            float titleText[] = {title};
            Table titleTable = new Table(titleText).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            titleTable.addCell(new Cell().add("Spare Parts / Stock Level Report").setBorderLeft(Border.NO_BORDER).setFontSize(12f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBold().setTextAlignment(TextAlignment.CENTER)
            );

            //Creating Table
            float colWidth[] = {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80 ,80, 80};
            Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                    .setMarginRight(-20f).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            information.addCell(new Cell().add("Part Name").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Code").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Manufacturer").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Vehicle Type").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Year(s)").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Price").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Initial Stock Level").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Initial Stock Price, £").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Used").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Delivery").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("New Stock Level").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Stock Cost £").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell().add("Low Level Threshold").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            information.addCell(new Cell(0,7).add("Total:").setBold().setMarginTop(-20f)
                            .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)
                    .setTextAlignment(TextAlignment.LEFT)
            );

            information.addCell(new Cell().add("Insert Value").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Empty Cells
            information.addCell(new Cell(0,3).add("").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            information.addCell(new Cell().add("Insert Value").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );

            float date = 150f;
            float columnDate[] = {date,date};
            Table time = new Table(columnDate).setMarginTop(20f).setTextAlignment(TextAlignment.LEFT)
                    ;
            time.addCell(new Cell().add("Report date:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            LocalDate now = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            time.addCell(new Cell().add(String.valueOf(now))
                    .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11).setMarginLeft(-20f)
            );

            float thankYou= 600f;
            float columnText[] = {thankYou};
            Table textTable = new Table(columnText);
            textTable.addCell(new Cell().add("\nSenior Storekeeper:\n\n ").setBorder(Border.NO_BORDER)
                    .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
            );

            textTable.addCell(new Cell().add("Ms E. Kournikova ").setBorder(Border.NO_BORDER)
                    .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
            );

            Document document=new Document(pdfdoc);
            document.add(name);
            document.add(location);
            document.add(titleTable);
            document.add(information);
            document.add(time);
            document.add(textTable);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}