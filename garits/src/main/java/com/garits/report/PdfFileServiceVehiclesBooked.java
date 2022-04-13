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

public class PdfFileServiceVehiclesBooked {

    public static void pdfCreationVehiclesBooked() {
    String filepathVehiclesBooked = "./pdf_reports/vehicles_booked.pdf";

    try
    {
        //Creating the page
        PdfWriter vehiclesBooked = new PdfWriter(filepathVehiclesBooked);
        PdfDocument pdfVehiclesBooked = new PdfDocument(vehiclesBooked);
        pdfVehiclesBooked.setDefaultPageSize(PageSize.A4.rotate());

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

        Document document=new Document(pdfVehiclesBooked);
        document.add(name);
        document.add(time);
        document.add(tableMonthly);
        document.add(information);
        document.add(amount);
        document.close();
    }
    catch(FileNotFoundException e){
        e.printStackTrace();
    }
}
}
