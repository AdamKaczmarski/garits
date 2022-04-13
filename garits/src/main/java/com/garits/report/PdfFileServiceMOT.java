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
public class PdfFileServiceMOT {

    public static void pdfCreationMOT() {

        String filepathMOT = "./pdf_reports/MoT_Reminder.pdf";



        try {
            //Creating the page
            PdfWriter stock = new PdfWriter(filepathMOT);
            PdfDocument pdfMOT = new PdfDocument(stock);
            pdfMOT.setDefaultPageSize(PageSize.A4);

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
            float columnAddress[] = {address,address};
            Table location = new Table(columnAddress).setMarginTop(10f).setTextAlignment(TextAlignment.LEFT)
                    .setMarginRight(30f)
                    ;

            location.addCell(new Cell(0,1).add("Quick Fix Fitters").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("19 High St.,").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("Ashford").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            location.addCell(new Cell(0,1).add("Kent CT16 8YY").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
                    .setFontSize(11));
            //Insert Customer Address
            location.addCell(new Cell(0,1).add("Insert Customer Address").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
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

            Document document=new Document(pdfMOT);
            document.add(name);
            document.add(location);
            document.add(time);
            document.add(reminderTable);
            document.add(infoText);
            document.add(textTable);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
