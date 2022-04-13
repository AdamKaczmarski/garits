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
public class PdfFileServiceInvoice {


    public static void pdfCreation()
    {

        String filepath="./pdf_reports/Invoice.pdf";


        try {
            //Creating the page
            PdfWriter writer=new PdfWriter(filepath);
            PdfDocument pdfdoc=new PdfDocument(writer);
            pdfdoc.setDefaultPageSize(PageSize.A4);

            //Creating the first column
            float col = 400f;
            float columnWidth[] = {col, col};
            Table name = new Table(columnWidth).setMarginTop(-20f).setMarginLeft(-20f).setMarginRight(-20f);


            name.addCell(new Cell().add("Receipt").setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginBottom(30f).setMarginTop(20f).setFontSize(25f).setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setMarginLeft(10f)
            );
            name.addCell(new Cell().add("Team 14 - Repo").setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginBottom(30f).setMarginTop(20f).setMarginRight(5f).setFontSize(25f).setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setMarginLeft(10f)
);

            //Creating Text
            float thankYouText = 400f;
            float column[] = {thankYouText};
            Table text = new Table(column).setMarginTop(10f).setMarginLeft(10f);
            text.addCell(new Cell().add("Thank you for shopping with Quick Fix Fitters Garage!").setBold()
                    .setMarginTop(-20f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12).setUnderline()
            );

            float address = 300f;
            float columnAddress[] = {address,address+50};
            Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)

                    ;
            //Insert customer's address here
            location.addCell(new Cell(0,1).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(11));

            float dear = 400f;
            float date = 100f;
            float columnDate[] = {dear,date,date};
            Table time = new Table(columnDate).setMarginTop(10f);
            time.addCell(new Cell().add("Dear").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(12));//Insert customer name here
            time.addCell(new Cell().add("Date:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12));
            LocalDate now = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            time.addCell(new Cell().add(String.valueOf(now))
                    .setMarginTop(-20f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12)
            );

            float invoice = 600f;
            float columnInvoice[] = {invoice};
            Table invoiceText = new Table(columnInvoice).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            //Insert Invoice Number here
            invoiceText.addCell(new Cell(0,5).add("INVOICE NO: ").setBold()
                    .setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setMarginLeft(10f));

            float info = 300f;
            float columnInfo[] = {info, info};
            Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            //Insert info here
            infoText.addCell(new Cell(0,2).add("Vehicle Registration No.: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Make: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Model: \n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Description of work: \n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            //Insert description of work here
            infoText.addCell(new Cell(0,2).add("1) Insert info here").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("2) Insert info here").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("3) Insert info here\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));


            //Creating Table
            float colWidth[] = {120, 120, 120, 120, 120};
            Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                    .setMarginRight(-20f).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            information.addCell(new Cell().add("Number:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Part No:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Unit Cost:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Quantity:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("Cost:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            information.addCell(new Cell().add("\nInsert data here").setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );


            information.addCell(new Cell().add("\n\nLabour").setBold().setMarginTop(-20f)
                    .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)

            );

            //Empty Cell for layout purposes
            information.addCell(new Cell().add("\n\n").setBold().setMarginTop(-20f)
                    .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)

            );

            //Insert total unit cost
            information.addCell(new Cell().add("\n\nInsert Value").setBold().setMarginTop(-20f)
                    .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)

            );
            //insert total quantity
            information.addCell(new Cell().add("\n\nInsert Value").setBold().setMarginTop(-20f)
                    .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)

            );
            //insert total cost
            information.addCell(new Cell().add("\n\nInsert Value").setBold().setMarginTop(-20f)
                    .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)


            );

            float total = 300f;
            float columnTotal[] = {total, total, total};
            Table totalTable = new Table(columnTotal).setMarginTop(30f).setMarginLeft(-40f)
                    .setBorder(Border.NO_BORDER).setFontSize(12).setMarginLeft(10);

            totalTable.addCell(new Cell(0,3).add("Subtotal:").setBold().setMarginTop(-20f)
                    .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)
            );

            totalTable.addCell(new Cell(0,3).add("VAT:").setBold().setMarginTop(-20f)
                    .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)
            );

            totalTable.addCell(new Cell(0,3).add("Total:").setBold().setMarginTop(-20f)
                    .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)
            );


            float thankYou= 600f;
            float columnText[] = {thankYou};
            Table textTable = new Table(columnText).setMarginTop(10f);
            textTable.addCell(new Cell().add("\nThank you for your valued custom. We look forward to receiving your payment in due course. \n\n\n ").setBorder(Border.NO_BORDER)
                    .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
            );

            textTable.addCell(new Cell().add("Yours sincerely, \n\n" + "G. Lancaster").setBorder(Border.NO_BORDER)
                    .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
            );



            Document document=new Document(pdfdoc);

            document.add(name);
            document.add(text);
            document.add(time);
            document.add(location);
            document.add(invoiceText);
            document.add(infoText);
            document.add(information);
            document.add(totalTable);
            document.add(textTable);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}