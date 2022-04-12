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
public class PdfFileServiceJobSheet {

    public static void pdfCreationJobSheet() {
        String filepathJobSheet = "C:\\Users\\adelz\\OneDrive - City, University of London\\Desktop\\PdfFiles\\garitsJobSheet.pdf";

        try {
            //Creating the page
            PdfWriter stock = new PdfWriter(filepathJobSheet);
            PdfDocument pdfStock = new PdfDocument(stock);
            pdfStock.setDefaultPageSize(PageSize.A4);

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
            jobText.addCell(new Cell().add("Job No: ").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setBold().setTextAlignment(TextAlignment.CENTER)
            );

            float info = 300f;
            float columnInfo[] = {info, info};
            //Insert Info here
            Table infoText = new Table(columnInfo).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            infoText.addCell(new Cell(0,2).add("Vehicle Registration No.: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Date Booked In: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Make: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Model: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Customer Name: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            infoText.addCell(new Cell(0,2).add("Tel.: ").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

            float description = 145f;
            float columnDescription[] = {description};
            Table descriptionText = new Table(columnDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            descriptionText.addCell(new Cell().add("Description of work required: ").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));



            float eTime = 600f;
            float columnEstimatedTime[] = {eTime};
            //Insert data here
            Table estimatedTime = new Table(columnEstimatedTime).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            estimatedTime.addCell(new Cell().add("Estimated time: ").setBorderLeft(Border.NO_BORDER).setFontSize(11f)
                    .setBorderRight(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            float fullDescription = 330f;
            float columnFullDescription[] = {fullDescription};
            Table fullDescriptionText = new Table(columnFullDescription).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            fullDescriptionText.addCell(new Cell().add("Description of work to be carried out (to be filled in on completion): ")
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            //Insert data here
            float actualTime = 600f;
            float columnActualTime[] = {actualTime};
            Table actualTimeText = new Table(columnActualTime).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            actualTimeText.addCell(new Cell().add("Actual Time: ")//Insert actual time here
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold());

            float sparePartsUsed = 240f;
            float columnSparePartsUsed[] = {sparePartsUsed};
            Table sparePartsText = new Table(columnSparePartsUsed).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            sparePartsText.addCell(new Cell().add("Spare parts used (to be filled in on completion): ")
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            float spareParts = 150f;
            float columnSpareParts[] = {spareParts, spareParts, spareParts};
            //Insert data here
            Table sparePart = new Table(columnSpareParts).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            sparePart.addCell(new Cell(0,1).add("Description: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            sparePart.addCell(new Cell(0,1).add("Part No.: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));
            sparePart.addCell(new Cell(0,1).add("Quantity: ").setBorder(Border.NO_BORDER).setFontSize(11f)
                    .setTextAlignment(TextAlignment.LEFT));

            float signature = 600f;
            float columnSignature[] = {signature};
            Table signatureText = new Table(columnSignature).setMarginTop(10f).setTextAlignment(TextAlignment.CENTER);
            signatureText.addCell(new Cell().add("Signature: ")
                    .setBorderLeft(Border.NO_BORDER).setFontSize(11f).setBorderRight(Border.NO_BORDER)
                    .setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            Document document=new Document(pdfStock);
            document.add(name);
            document.add(location);
            document.add(time);
            document.add(jobText);
            document.add(infoText);
            document.add(descriptionText);
            document.add(estimatedTime);
            document.add(fullDescriptionText);
            document.add(actualTimeText);
            document.add(sparePartsText);
            document.add(sparePart);
            document.add(signatureText);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
