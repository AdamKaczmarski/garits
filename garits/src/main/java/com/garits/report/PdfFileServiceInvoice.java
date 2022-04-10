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
        String filepath="C:\\Users\\adelz\\OneDrive - City, University of London\\Desktop\\PdfFiles\\garits.pdf";

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

            //Creating Table
            float colWidth[] = {100, 100, 100, 100, 100};
            Table information = new Table(colWidth).setMarginTop(30f).setMarginLeft(-20f)
                    .setMarginRight(-20f).setBorder(Border.NO_BORDER).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER).setMarginLeft(10);

            information.addCell(new Cell(0,5).add("Invoice").setBold()
                    .setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBorderLeft(Border.NO_BORDER).setMarginLeft(10f));

            information.addCell(new Cell().add("Number:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setBorder(Border.NO_BORDER)
                    .setFontSize(11)
            );

            information.addCell(new Cell().add("Reference:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setBorder(Border.NO_BORDER)
                    .setFontSize(11)
            );

            information.addCell(new Cell().add("Quantity:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setBorder(Border.NO_BORDER)
                    .setFontSize(11)
            );

            information.addCell(new Cell().add("Part Code:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setBorder(Border.NO_BORDER)
                    .setFontSize(11)
            );

            information.addCell(new Cell().add("Price:").setBold().setMarginTop(-20f)
                    .setMarginLeft(-20f).setMarginRight(-20f).setBorder(Border.NO_BORDER)
                    .setFontSize(11)
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



            Document document=new Document(pdfdoc);

            document.add(name);
            document.add(text);
            document.add(time);
            document.add(information);
            document.add(totalTable);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}