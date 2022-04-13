package com.garits.pdf;

import com.garits.job.Job;
import com.garits.part.Part;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GeneratePdfReport {
    public static ByteArrayInputStream stockLedger() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //String filepathStock = "./pdf_reports/stock_ledger.pdf";

        //Creating the page
        PdfWriter stock = new PdfWriter(out);
        PdfDocument pdfStock = new PdfDocument(stock);
        pdfStock.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfStock);

/*
            PdfWriter stock = new PdfWriter(filepathStock);
            PdfDocument pdfStock = new PdfDocument(stock);
            pdfStock.setDefaultPageSize(PageSize.A4.rotate());
*/


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
        float columnDate[] = {date, date};
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


        //Document document=new Document(pdfStock);

        document.add(name);
        document.add(time);
        document.add(information);
        document.close();

        pdfStock.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream motReminder() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfMot = new PdfDocument(pdfWriter);
        pdfMot.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfMot);
        //Creating the first column
        float col = 600f;
        float columnWidth[] = {col};
        Table name = new Table(columnWidth).setMarginTop(-20f)
                .setTextAlignment(TextAlignment.RIGHT);


        name.addCell(new Cell().add("Team 14 - Repo").setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginBottom(30f).setMarginTop(20f).setBorderLeft(Border.NO_BORDER).setFontSize(25f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setMarginLeft(10f)
        );

        float address = 500f;
        float columnAddress[] = {address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);

        location.addCell(new Cell(0, 1).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));


        float dear = 400f;
        float date = 100f;
        float columnDate[] = {dear, date, date};
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

        float reminder = 600f;
        float reminderMOT[] = {reminder};
        Table reminderTable = new Table(reminderMOT).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        reminderTable.addCell(new Cell().add("REMINDER - MoT TEST DUE ").setBorderLeft(Border.NO_BORDER).setFontSize(12f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBold().setTextAlignment(TextAlignment.CENTER)
        );

        float info = 300f;
        float columnInfo[] = {info, info};
        Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        infoText.addCell(new Cell().add("Vehicle Registration No.: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.CENTER).setBold());//Insert Reg No here
        infoText.addCell(new Cell().add("Renewal Test Date:  ").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.CENTER).setBold());//Insert Renewal date here

        float text = 600f;
        float columnText[] = {text};
        Table textTable = new Table(columnText).setMarginTop(10f);
        textTable.addCell(new Cell().add("\nAccording to our records, the above vehicle is due to have its MoT certificate renewed on the date shown.\n" +
                        "Account Holders customers such as yourself are assured of our prompt attention, and we hope that you will use our\n" +
                        "services on this occasion in order to have the necessary test carried out on your vehicle.\n\n\n\n ").setBorder(Border.NO_BORDER)
                .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
        );

        textTable.addCell(new Cell().add("Yours sincerely, \n\n" + "G. Lancaster").setBorder(Border.NO_BORDER)
                .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
        );
        document.add(name);
        document.add(location);
        document.add(time);
        document.add(reminderTable);
        document.add(infoText);
        document.add(textTable);
        document.close();
        pdfMot.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream invoice() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfInvoice = new PdfDocument(pdfWriter);
        pdfInvoice.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfInvoice);
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
        pdfInvoice.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream jobSheet(Job j) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfJobSheet = new PdfDocument(pdfWriter);
        pdfJobSheet.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfJobSheet);
        //Creating the first column
        float col = 600f;
        float columnWidth[] = {col};
        Table name = new Table(columnWidth).setMarginTop(-20f)
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

        float job = 600f;
        float jobNo[] = {job};
        //Insert Job No here
        Table jobText = new Table(jobNo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        jobText.addCell(new Cell().add("Job No: "+j.getIdJob()).setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBold().setTextAlignment(TextAlignment.CENTER)
        );

        float info = 300f;
        float columnInfo[] = {info, info};
        //Insert Info here
        Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        infoText.addCell(new Cell(0,2).add("Vehicle Registration No.: "+j.getVehicle().getIdRegNo()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0,2).add("Date Booked In: "+j.getBookingDate()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0,2).add("Make: "+j.getVehicle().getManufacturer()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0,2).add("Model: "+j.getVehicle().getModel()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0,2).add("Customer Name: "+j.getVehicle().getCustomer().iterator().next().getName()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0,2).add("Tel.: "+j.getVehicle().getCustomer().iterator().next().getTelephoneNumber()).setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT));

        float description = 600f;
        float columnDescription[] = {description};
        Table descriptionText = new Table(columnDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        descriptionText.addCell(new Cell().add("Description of work required: ").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBold()
                .setTextAlignment(TextAlignment.LEFT));
        descriptionText.addCell(new Cell().add(j.getDescriptionRequired()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));


        float eTime = 600f;
        float columnEstimatedTime[] = {eTime};
        //Insert data here
        Table estimatedTime = new Table(columnEstimatedTime).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        estimatedTime.addCell(new Cell().add("Estimated time: "+j.getEstTimeMin()+" min").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBold()
                .setTextAlignment(TextAlignment.CENTER));

        float fullDescription = 600f;
        float columnFullDescription[] = {fullDescription};
        Table fullDescriptionText = new Table(columnFullDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        fullDescriptionText.addCell(new Cell().add("Description of work carried out: ")
                .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setBorderBottom(Border.NO_BORDER).setBold());
        fullDescriptionText.addCell(new Cell().add(j.getDescriptionDone()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        //Insert data here
        float actualTime = 600f;
        float columnActualTime[] = {actualTime};
        Table actualTimeText = new Table(columnActualTime).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        actualTimeText.addCell(new Cell().add("Actual Time: "+j.getActTimeMin()+" min")//Insert actual time here
                .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold());
        Table sparePartsText=null;
        Table sparePart=null;
        if (j.getParts() != null && !j.getParts().isEmpty()) {
            float sparePartsUsed = 400f;
            float columnSparePartsUsed[] = {sparePartsUsed};
            sparePartsText = new Table(columnSparePartsUsed).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            sparePartsText.addCell(new Cell().add("Spare parts used: ")
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            float spareParts = 150f;
            float columnSpareParts[] = {spareParts, spareParts, spareParts};
            //Insert data here
            sparePart = new Table(columnSpareParts).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            sparePart.addCell(new Cell(0, 1).add("Description: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            sparePart.addCell(new Cell(0, 1).add("Part No.: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            sparePart.addCell(new Cell(0, 1).add("Quantity: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            for (Part p : j.getParts()) {
                sparePart.addCell(new Cell(0, 1).add(p.getPartName()).setBorder(Border.NO_BORDER).setFontSize(11f)
                        .setTextAlignment(TextAlignment.LEFT));
                sparePart.addCell(new Cell(0, 1).add(p.getCode()).setBorder(Border.NO_BORDER).setFontSize(11f)
                        .setTextAlignment(TextAlignment.LEFT));
                sparePart.addCell(new Cell(0, 1).add(p.getQuantityUsed().toString()).setBorder(Border.NO_BORDER).setFontSize(11f)
                        .setTextAlignment(TextAlignment.LEFT));
            }
        }
        float signature = 600f;
        float columnSignature[] = {signature};
        Table signatureText = new Table(columnSignature).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        signatureText.addCell(new Cell().add("Signature: ")
                .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        document.add(name);
        document.add(location);
        document.add(time);
        document.add(jobText);
        document.add(infoText);
        document.add(descriptionText);
        document.add(estimatedTime);
        document.add(fullDescriptionText);
        document.add(actualTimeText);
        if (j.getParts()!=null && !j.getParts().isEmpty()) {
            document.add(sparePartsText);
            document.add(sparePart);
        }
        document.add(signatureText);
        document.close();
        pdfJobSheet.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream averagesReport() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfAverages = new PdfDocument(pdfWriter);
        pdfAverages.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfAverages);
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
        pdfAverages.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream bookingStats() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfBookingStats = new PdfDocument(pdfWriter);
        pdfBookingStats.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfBookingStats);
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

        float Monthly = 1000f;
        float columnMonthly[] = {Monthly};
        Table tableMonthly = new Table(columnMonthly).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        tableMonthly.addCell(new Cell().add("Number of Monthly Vehicles Booked on a Monthly Basis ").setBorderLeft(Border.NO_BORDER).setFontSize(12f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBold().setTextAlignment(TextAlignment.CENTER).setFontSize(30f)
        );

        //Creating Table
        float colWidth[] = {300,300,300};
        Table information = new Table(colWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        information.addCell(new Cell().add("Job Type").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        information.addCell(new Cell().add("Service Dates").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        information.addCell(new Cell().add("Type of Customer").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        //Insert data
        information.addCell(new Cell().add("Insert data here").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert data
        information.addCell(new Cell().add("Insert data here").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert data
        information.addCell(new Cell().add("Insert data here").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );


        //Creating Table
        float colAmount[] = {140,140,140,140,140,140};
        Table amount = new Table(colAmount).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        amount.addCell(new Cell().add("Account Holder").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        amount.addCell(new Cell().add("Annual Service").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        amount.addCell(new Cell().add("MoT").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        amount.addCell(new Cell().add("Vehicle Repair").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        amount.addCell(new Cell().add("Casual").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        amount.addCell(new Cell().add("Total Vehicles").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );
        //Insert Value
        amount.addCell(new Cell().add("Insert value").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );

        document.add(name);
        document.add(time);
        document.add(tableMonthly);
        document.add(information);
        document.add(amount);
        document.close();
        pdfBookingStats.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream partsOrder() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfPartsOrder = new PdfDocument(pdfWriter);
        pdfPartsOrder.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfPartsOrder);
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

        document.add(name);
        document.add(location);
        document.add(time);
        document.add(infoText);
        document.add(information);
        document.add(signatureText);
        document.close();
        pdfPartsOrder.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
