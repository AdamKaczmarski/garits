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

@Service
public class PdfFileServicePartsOrder {


    public static void pdfCreationStock() {
        String filepathStock = "./pdf_reports/parts_orders.pdf";

        try {
            //Creating the page
            PdfWriter stock = new PdfWriter(filepathStock);
            PdfDocument pdfStock = new PdfDocument(stock);
            pdfStock.setDefaultPageSize(PageSize.A4);

            //Creating the first column
            float col = 600f;
            float columnWidth[] = {col};
            Table name = new Table(columnWidth).setMarginTop(-20f).setMarginLeft(-20f).setMarginRight(-20f)
                    .setTextAlignment(TextAlignment.RIGHT);


            name.addCell(new Cell().add("Team 14 - Repo").setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginBottom(30f).setMarginTop(20f).setBorderLeft(Border.NO_BORDER).setFontSize(25f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setMarginLeft(10f)
            );

            float address = 100f;
            float columnAddress[] = {address,address,address,address};
            Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                    .setMarginRight(30f)
                    ;

            location.addCell(new Cell(0,4).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,4).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,4).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,4).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11)

            );

            float date = 100f;
            float columnDate[] = {date,date};
            Table time = new Table(columnDate).setMarginTop(10f).setTextAlignment(TextAlignment.RIGHT)
                    .setMarginLeft(360f);
            time.addCell(new Cell().add("Date:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12));
            LocalDate now = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            time.addCell(new Cell().add(String.valueOf(now))
                    .setMarginTop(-20f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12).setMarginLeft(20f)
            );

            float info = 300f;
            float columnInfo[] = {info, info};
            Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            //Insert Company name here
            infoText.addCell(new Cell(0,2).add("\n\nCompany: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            //Might need dynamic info for address
            infoText.addCell(new Cell(0,2).add("Address: 25 The Causeway, Staines, Middlesex\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Tel: 01784 407862\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Fax: 01784 407862\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));


            float colWidth[] = {150, 150, 150, 150};
            Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                    .setMarginRight(-20f).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            information.addCell(new Cell().add("Order Number").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Description").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Quantity").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Price").setBold().setMarginTop(-20f)
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

            float signature = 600f;
            float columnSignature[] = {signature};
            Table signatureText = new Table(columnSignature).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER)
                    .setMarginLeft(-20f).setMarginRight(-20f);
            signatureText.addCell(new Cell().add("\n\nSignature: ")
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
            
            Document document=new Document(pdfStock);
            document.add(name);
            document.add(location);
            document.add(time);
            document.add(infoText);
            document.add(information);
            document.add(signatureText);
            document.close();
        }
    catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }