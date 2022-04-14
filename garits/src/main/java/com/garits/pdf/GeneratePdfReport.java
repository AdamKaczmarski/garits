package com.garits.pdf;

import com.garits.customer.Customer;
import com.garits.job.Job;
import com.garits.order.Order;
import com.garits.order.PartsOrdersDetail;
import com.garits.part.Part;
import com.garits.payment.job.PaymentJob;
import com.garits.vehicle.Vehicle;
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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class GeneratePdfReport {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    //DONE
    public static ByteArrayInputStream stockLedger(Iterable<Part> parts) {

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
        for (Part p : parts) {
            //Insert data
            information.addCell(new Cell().add("\n" + p.getPartName()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getCode()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getManufacturer()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getVehicleType()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getYearS()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getPrice()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getStockLevel()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }

        //Document document=new Document(pdfStock);

        document.add(name);
        document.add(time);
        document.add(information);
        document.close();

        pdfStock.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    //DONE
    public static ByteArrayInputStream motReminder(Vehicle v) {
        System.out.println("Generating MOT Reminder");
        Customer c = v.getCustomer().iterator().next();
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

        float address = 600f;
        float columnAddress[] = {address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);
//Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getAddress()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getCity() + " " + c.getPostcode()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));


        float dear = 400f;
        float date = 100f;
        float columnDate[] = {dear, date, date};
        Table time = new Table(columnDate).setMarginTop(10f);
        time.addCell(new Cell().add("Dear " + c.getName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
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
        infoText.addCell(new Cell().add("Vehicle Registration No.: " + v.getIdRegNo()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.CENTER).setBold());//Insert Reg No here
        DateTimeFormatter dtfFromDb = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        //String dateString = v.getLastMot().toString().substring(0,10);
        LocalDateTime dT = LocalDateTime.parse(v.getLastMot().toString(), dtfFromDb);
        dT = dT.plusYears(1);
        infoText.addCell(new Cell().add("Renewal Test Date:  " + dT.toString().substring(0, 10)).setBorder(Border.NO_BORDER).setFontSize(11f)
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

    //DONE
    public static ByteArrayInputStream invoice(Job j, PaymentJob p) {
        Customer c = j.getVehicle().getCustomer().iterator().next();
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
        float columnAddress[] = {address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);
//Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getAddress()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add(c.getCity() + " " + c.getPostcode()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        //Insert Customer Address
        location.addCell(new Cell(0, 1).add("").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 1).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));

        float dear = 400f;
        float date = 100f;
        float columnDate[] = {dear, date, date};
        Table time = new Table(columnDate).setMarginTop(10f);
        time.addCell(new Cell().add("Dear " + c.getName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
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
        invoiceText.addCell(new Cell(0, 5).add("INVOICE NO: " + p.getIdPayment()).setBold()
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER).setMarginLeft(10f));

        float info = 300f;
        float columnInfo[] = {info, info};
        Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        //Insert info here
        infoText.addCell(new Cell(0, 2).add("Vehicle Registration No.: " + j.getVehicle().getIdRegNo()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Make: " + j.getVehicle().getManufacturer()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Model: " + j.getVehicle().getModel() + "\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Description of work:\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        //Insert description of work here
        infoText.addCell(new Cell(0, 2).add(j.getDescriptionDone()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        /*infoText.addCell(new Cell(0, 2).add("2) Insert info here").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("3) Insert info here\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));*/


        //Creating Table
        float colWidth[] = {120, 120, 120, 120, 120};
        Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                .setMarginRight(-20f).setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        information.addCell(new Cell().add("Item:").setBold().setMarginTop(-20f)
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
        for (Part part : j.getParts()) {
            information.addCell(new Cell().add(part.getPartName()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            information.addCell(new Cell().add(part.getCode()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            information.addCell(new Cell().add("£" + df.format(part.getPrice())).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            information.addCell(new Cell().add(part.getQuantityUsed().toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            Double cost = part.getPrice() * part.getQuantityUsed();
            information.addCell(new Cell().add("£" + df.format(cost).toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

        }


        information.addCell(new Cell().add("\n\nLabour").setBold().setMarginTop(-20f)
                .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)

        );

        //Empty Cell for layout purposes
        information.addCell(new Cell().add("\n\n").setBold().setMarginTop(-20f)
                .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)

        );

        //Insert total unit cost
        /*Double totalUnitCost = 0.0;
        Integer totalQuantity=0;
        Double totalCost=0.0;
        for (Part part : j.getParts()){
            totalUnitCost += part.getPrice();
            totalQuantity+=part.getQuantityUsed();
            totalCost=totalUnitCost*totalQuantity;
        }*/
        information.addCell(new Cell().add("\n\n£" + df.format(j.getUser().iterator().next().getRoles().iterator().next().getHourlyRate())).setBold().setMarginTop(-20f)
                .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.CENTER)

        );
        //insert total quantity
        information.addCell(new Cell().add("\n\n" + j.getActTimeMin()).setBold().setMarginTop(-20f)
                .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)

        );
        //insert total cost
        double labourCost = (j.getActTimeMin() / 60) * j.getUser().iterator().next().getRoles().iterator().next().getHourlyRate();
        information.addCell(new Cell().add("\n\n£" + df.format(labourCost)).setBold().setMarginTop(-20f)
                .setFontSize(11).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER)


        );

        float total = 300f;
        float columnTotal[] = {total, total, total};
        Table totalTable = new Table(columnTotal).setMarginTop(30f).setMarginLeft(-40f)
                .setBorder(Border.NO_BORDER).setFontSize(12).setMarginLeft(10);
        Double totalCostWithoutVat = p.getAmount() / 1.2;
        totalTable.addCell(new Cell(0, 3).add("Subtotal: \t\t\t£" + df.format(totalCostWithoutVat).toString()).setBold().setMarginTop(-20f)
                .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.RIGHT)
        );

        totalTable.addCell(new Cell(0, 3).add("VAT: \t\t\t£" + df.format(p.getAmount() - totalCostWithoutVat)).setBold().setMarginTop(-20f)
                .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.RIGHT)
        );

        totalTable.addCell(new Cell(0, 3).add("Total: \t\t\t£" + df.format(p.getAmount())).setBold().setMarginTop(-20f)
                .setBorder(Border.NO_BORDER).setFontSize(11).setTextAlignment(TextAlignment.RIGHT)
        );


        float thankYou = 600f;
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
        document.add(location);
        document.add(time);
        document.add(invoiceText);
        document.add(infoText);
        document.add(information);
        document.add(totalTable);
        document.add(textTable);
        document.close();
        pdfInvoice.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    //DONE
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
        float columnAddress[] = {address, address, address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);

        location.addCell(new Cell(0, 4).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11)

        );


        float date = 100f;
        float columnDate[] = {date, date};
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
        jobText.addCell(new Cell().add("Job No: " + j.getIdJob()).setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBold().setTextAlignment(TextAlignment.CENTER)
        );

        float info = 300f;
        float columnInfo[] = {info, info};
        //Insert Info here
        Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        infoText.addCell(new Cell(0, 2).add("Vehicle Registration No.: " + j.getVehicle().getIdRegNo()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Date Booked In: " + j.getBookingDate()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Make: " + j.getVehicle().getManufacturer()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Model: " + j.getVehicle().getModel()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Customer Name: " + j.getVehicle().getCustomer().iterator().next().getName()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Tel.: " + j.getVehicle().getCustomer().iterator().next().getTelephoneNumber()).setBorderLeft(Border.NO_BORDER).setFontSize(11f)
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
        estimatedTime.addCell(new Cell().add("Estimated time: " + j.getEstTimeMin() + " min").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
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
        actualTimeText.addCell(new Cell().add("Actual Time: " + j.getActTimeMin() + " min")//Insert actual time here
                .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold());
        Table sparePartsText = null;
        Table sparePart = null;
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
        if (j.getParts() != null && !j.getParts().isEmpty()) {
            document.add(sparePartsText);
            document.add(sparePart);
        }
        document.add(signatureText);
        document.close();
        pdfJobSheet.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    //DONE
    public static ByteArrayInputStream averagesReport(List<Object[]> r1, List<Object[]> r2, List<Object[]> r3) {
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
        MoTdescriptionText.addCell(new Cell().add("Overall").setFontSize(15f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
        MoTdescriptionText.addCell(new Cell().add("The average time and average cost of all services.").setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        float colWidth[] = {300, 300};
        Table averageOverall = new Table(colWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        averageOverall.addCell(new Cell().add("Average Time").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        averageOverall.addCell(new Cell().add("Average Cost Per Job").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        averageOverall.addCell(new Cell().add(r1.get(0)[0].toString()).setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );

        averageOverall.addCell(new Cell().add("£" + r1.get(0)[1].toString()).setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorder(Border.NO_BORDER)
        );


        float vehicleDescription = 600f;
        float columnvehicleDescription[] = {vehicleDescription};
        Table vehicleDescriptionText = new Table(columnvehicleDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        vehicleDescriptionText.addCell(new Cell().add("Vehicle Repairs").setFontSize(15f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
        vehicleDescriptionText.addCell(new Cell().add("The average time and average cost for vehicle repairs.").setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        //Creating Table
        float colWidthPerJob[] = {200, 200, 200};
        Table averagePerJob = new Table(colWidthPerJob).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);
        averagePerJob.addCell(new Cell().add("Job Type").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        averagePerJob.addCell(new Cell().add("Average Time").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        averagePerJob.addCell(new Cell().add("Average Cost Per Job").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        for (Object[] row : r2) {
            averagePerJob.addCell(new Cell().add(row[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            averagePerJob.addCell(new Cell().add(row[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            averagePerJob.addCell(new Cell().add("£" + row[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }
        float annualService = 600f;
        float columnAnnualService[] = {annualService};
        Table annualServiceText = new Table(columnAnnualService).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        annualServiceText.addCell(new Cell().add("Average per Mechanic").setFontSize(15f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold());
        annualServiceText.addCell(new Cell().add("The average time and average cost per job per mechanic.").setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        float colWidthPerJobPerMechanic[] = {150, 150, 150, 150};
        Table averagePerJobPerMechanic = new Table(colWidthPerJobPerMechanic).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);
        averagePerJobPerMechanic.addCell(new Cell().add("Job Type").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        averagePerJobPerMechanic.addCell(new Cell().add("Mechanic").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        averagePerJobPerMechanic.addCell(new Cell().add("Average Time").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        averagePerJobPerMechanic.addCell(new Cell().add("Average Cost Per Job").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        for (Object[] row : r3) {
            averagePerJobPerMechanic.addCell(new Cell().add(row[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            averagePerJobPerMechanic.addCell(new Cell().add(row[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            averagePerJobPerMechanic.addCell(new Cell().add(row[2].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );

            averagePerJobPerMechanic.addCell(new Cell().add("£"+row[3].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }

        document.add(name);
        document.add(time);
        document.add(tableAverages);
        document.add(MoTdescriptionText);
        document.add(averageOverall);
        document.add(vehicleDescriptionText);
        document.add(averagePerJob);
        document.add(annualServiceText);
        document.add(averagePerJobPerMechanic);
        document.close();
        pdfAverages.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    //TBD - DO AFTER ADDING CUSTOMER ACCOUNT HOLDER
    public static ByteArrayInputStream bookingStats(List<Object[]> resultsPerMonthPerYear, List<Object[]> resultsOverall, List<Object[]> resultsPerJob,List<Object[]> r4,List<Object[]> r5) {
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

        float Monthly = 1000f;
        float columnMonthly[] = {Monthly};
        Table tableMonthly = new Table(columnMonthly).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        tableMonthly.addCell(new Cell().add("Vehicle Bookings Stats").setBorderLeft(Border.NO_BORDER).setFontSize(12f)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBold().setTextAlignment(TextAlignment.CENTER).setFontSize(30f)
        );
        float totalWidth[] = {300f, 300f};
        Table total = new Table(totalWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        total.addCell(new Cell().add("Total bookings: ").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        total.addCell(new Cell().add(resultsOverall.get(0)[0].toString()).setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );
        //Creating Table
        float colWidth[] = {300, 300};
        Table information = new Table(colWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        information.addCell(new Cell().add("Job Type").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        information.addCell(new Cell().add("Amount of Bookings").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );


        for (Object[] x : resultsPerJob) { //Insert data
            information.addCell(new Cell().add(x[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add(x[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }
        //Creating Table
        float monthlyColWidth[] = {300, 300};
        Table perMonthPerYear = new Table(monthlyColWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        perMonthPerYear.addCell(new Cell().add("Year - Month").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        perMonthPerYear.addCell(new Cell().add("Amount of Bookings").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );


        for (Object[] x : resultsPerMonthPerYear) { //Insert data
            perMonthPerYear.addCell(new Cell().add(x[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            perMonthPerYear.addCell(new Cell().add(x[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }


        //Creating Table
        float accHolderWidth[] = {300, 300};
        Table accHolder = new Table(accHolderWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        accHolder.addCell(new Cell().add("Year - Month").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        accHolder.addCell(new Cell().add("Amount of Bookings by account holders").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );


        for (Object[] x : r4) { //Insert data
            accHolder.addCell(new Cell().add(x[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            accHolder.addCell(new Cell().add(x[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }
        float casualWidth[] = {300, 300};
        Table casual = new Table(casualWidth).setMarginTop(20f)
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER);

        casual.addCell(new Cell().add("Year - Month").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        casual.addCell(new Cell().add("Amount of Bookings by casual customers").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );


        for (Object[] x : r5) { //Insert data
            casual.addCell(new Cell().add(x[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            casual.addCell(new Cell().add(x[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }


        document.add(name);
        document.add(time);
        document.add(tableMonthly);
        document.add(total);
        document.add(information);
        document.add(perMonthPerYear);
        document.add(accHolder);
        document.add(casual);
        document.close();
        pdfBookingStats.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    //DONE
    public static ByteArrayInputStream partsOrder(Order o, Iterable<PartsOrdersDetail> pod) {
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
        float columnAddress[] = {address, address, address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);

        location.addCell(new Cell(0, 4).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        location.addCell(new Cell(0, 4).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11)

        );

        float date = 100f;
        float columnDate[] = {date, date};
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
        infoText.addCell(new Cell(0, 2).add("\n\nCompany: "+o.getCompany()).setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        //Might need dynamic info for address
        infoText.addCell(new Cell(0, 2).add("Address: "+o.getAddress()+"\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Tel: "+o.getPhoneNo()+"\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));
        infoText.addCell(new Cell(0, 2).add("Fax: "+o.getFax()+"\n\n").setBorder(Border.NO_BORDER).setFontSize(11f)
                .setTextAlignment(TextAlignment.LEFT));

        float invoice = 600f;
        float columnInvoice[] = {invoice};
        Table orderText = new Table(columnInvoice).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
        //Insert Invoice Number here
        orderText.addCell(new Cell(0, 5).add("ORDER NO: " + o.getIdOrder()).setBold()
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER).setMarginLeft(10f));

        float colWidth[] = {150, 150, 150, 150};
        Table information = new Table(colWidth).setMarginTop(20f).setMarginLeft(-20f)
                .setMarginRight(-20f).setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        information.addCell(new Cell().add("Part Code").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER)
        );

        information.addCell(new Cell().add("Part Name").setBold().setMarginTop(-20f)
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
        for (PartsOrdersDetail p : pod) {
            //Insert data
            information.addCell(new Cell().add("\n" + p.getPart().getCode()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getPart().getPartName()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n" + p.getQuantityOrdered()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
            //Insert data
            information.addCell(new Cell().add("\n£" + df.format(p.getPart().getPrice())).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                    .setBorder(Border.NO_BORDER)
            );
        }
        //Layout cell
        information.addCell(new Cell().add("\n").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER));
        //Layout cell
        information.addCell(new Cell().add("\n").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER));
        information.addCell(new Cell().add("\n Total:").setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER).setBold());
        information.addCell(new Cell().add("\n£" + df.format(o.getOrderAmount())).setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(11)
                .setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER).setBold());

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
        document.add(orderText);
        document.add(information);
        document.add(signatureText);
        document.close();
        pdfPartsOrder.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    //DONE
    public static ByteArrayInputStream stockLevel(List<Object[]> items) {
        //Creating the page
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfStockLevel = new PdfDocument(pdfWriter);
        pdfStockLevel.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfStockLevel);


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
        float columnAddress[] = {address, address, address, address};
        Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                .setMarginRight(30f);

        location.addCell(new Cell(0, 4).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10));
        location.addCell(new Cell(0, 4).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10));
        location.addCell(new Cell(0, 4).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10));
        location.addCell(new Cell(0, 4).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
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
        float colWidth[] = {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
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
        Double totalInitCost = 0.0;
        Double totalStockCost = 0.0;
        for (Object[] item : items) {
            totalInitCost += Double.parseDouble(item[7].toString());
            totalStockCost += Double.parseDouble(item[11].toString());

            //Insert data
            information.addCell(new Cell().add("\n" + item[0].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[1].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[2].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[3].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[4].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n£" + item[5].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[6].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n£" + item[7].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[8].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[9].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n" + item[10].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            //Insert data
            information.addCell(new Cell().add("\n£" + item[11].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
            information.addCell(new Cell().add("\n" + item[12].toString()).setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

            );
        }
        information.addCell(new Cell(0, 7).add("Total:").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)
                .setTextAlignment(TextAlignment.LEFT)
        );

        information.addCell(new Cell().add("£" + df.format(totalInitCost)).setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

        );
        //Empty Cells
        information.addCell(new Cell(0, 3).add("").setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

        );
        information.addCell(new Cell().add("£" + df.format(totalStockCost)).setBold().setMarginTop(-20f)
                .setMarginLeft(-20f).setMarginRight(-20f).setFontSize(10)

        );

        float date = 150f;
        float columnDate[] = {date, date};
        Table time = new Table(columnDate).setMarginTop(20f).setTextAlignment(TextAlignment.LEFT);
        time.addCell(new Cell().add("Report date:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11));
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        time.addCell(new Cell().add(String.valueOf(now))
                .setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                .setFontSize(11).setMarginLeft(-20f)
        );

        float thankYou = 600f;
        float columnText[] = {thankYou};
        Table textTable = new Table(columnText);
        textTable.addCell(new Cell().add("\nSenior Storekeeper:\n\n ").setBorder(Border.NO_BORDER)
                .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
        );

        textTable.addCell(new Cell().add("Ms E. Kournikova ").setBorder(Border.NO_BORDER)
                .setFontSize(11f).setTextAlignment(TextAlignment.LEFT)
        );

        document.add(name);
        document.add(location);
        document.add(titleTable);
        document.add(information);
        document.add(time);
        document.add(textTable);
        document.close();
        pdfStockLevel.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
