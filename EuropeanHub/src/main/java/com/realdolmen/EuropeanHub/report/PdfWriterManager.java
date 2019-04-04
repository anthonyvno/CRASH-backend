package com.realdolmen.EuropeanHub.report;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;

public class PdfWriterManager {

    private Report report;

    public PdfWriterManager(Report report) {
        this.report = report;
    }

    public String generatePDF() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\SBZBN83\\Pictures\\" + report.getDateReportReceived().getTime() + "_aanrijdingsformulier.pdf"));

        document.open();
        PdfContentByte canvas;
        canvas = writer.getDirectContentUnder();

        Image img = Image.getInstance("C:\\Users\\SBZBN83\\Pictures\\aanrijdingsform.PNG");
        img.setAbsolutePosition(0F, 0F);
        img.scaleAbsolute(595F, 842F);

        canvas.addImage(img);

        //Aanrijdingsgegevens
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getDateCrash().getDate() + "/" + (report.getDateCrash().getMonth() + 1) + "/" + (report.getDateCrash().getYear() + 1900),
                        new Font(Font.FontFamily.COURIER, 9F)),
                0.40F,
                0.91F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getDateCrash().getHours() + ":" + report.getDateCrash().getMinutes(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                2.69F,
                0.87F
        );

        setPara(
                writer.getDirectContent(),
                new Phrase(report.getCountry(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                4.21F,
                0.87F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getPostalCode() + " " + report.getCity(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                6.01F,
                0.55F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getStreet() + " " + report.getStreetNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                5.38F,
                0.87F
        );
        // VERZEKERINGSNEMER A
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getLastName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.10F,
                3.07F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getEmail(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                1.61F,
                4.34F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getFirstName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.38F,
                3.37F
        );
        // VOERTUIG A
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getBrand() + " " + report.getProfiles().get(0).getVehicles().get(0).getModel(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                0.44F,
                5.50F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getLicensePlate(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                0.44F,
                6.03F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getCountry(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                0.44F,
                6.58F
        );

        // VERZEKERING A
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getInsurance().getInsurer().getName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.10F,
                7.28F
        );

        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getInsurance().getInsuranceNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.46F,
                7.56F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getInsurance().getGreenCardNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                2.22F,
                7.87F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getInsurance().getExpires().getDate() + "/"
                        + (report.getProfiles().get(0).getVehicles().get(0).getInsurance().getExpires().getMonth() + 1) + "/"
                        + (report.getProfiles().get(0).getVehicles().get(0).getInsurance().getExpires().getYear() + 1900),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                4.13F,
                8.38F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getVehicles().get(0).getInsurance().getEmailAgency(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                1.63F,
                10F
        );

        // BESTUURDER A
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getLastName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.23F,
                11.16F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getFirstName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.42F,
                11.47F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getEmail(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                1.67F,
                12.69F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getLicense().getLicenseNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                1.55F,
                12.98F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getLicense().getCategory(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                2.18F,
                13.27F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(0).getLicense().getExpires(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                2.14F,
                13.57F
        );

        /*
        **********************************************************************
        **********************************************************************
        ************************BESTUURDER B**********************************
        **********************************************************************
        **********************************************************************
         */
        // VERZEKERINGSNEMER B
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getLastName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                9.80F,
                3.07F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getEmail(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                10.35F,
                4.34F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getFirstName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.12F,
                3.37F
        );
        // VOERTUIG A
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getBrand() + " " + report.getProfiles().get(1).getVehicles().get(0).getModel(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                9.14F,
                5.50F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getLicensePlate(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                9.14F,
                6.03F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getCountry(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                9.10F,
                6.58F
        );

        // VERZEKERING B
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getInsurance().getInsurer().getName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                9.78F,
                7.28F
        );

        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getInsurance().getInsuranceNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.14F,
                7.56F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getInsurance().getGreenCardNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.92F,
                7.87F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getInsurance().getExpires().getDate() + "/"
                        + (report.getProfiles().get(1).getVehicles().get(0).getInsurance().getExpires().getMonth() + 1) + "/"
                        + (report.getProfiles().get(1).getVehicles().get(0).getInsurance().getExpires().getYear() + 1900),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                12.83F,
                8.38F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getVehicles().get(0).getInsurance().getEmailAgency(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                10.31F,
                10F
        );

        // BESTUURDER B
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getLastName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                9.80F,
                11.16F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getFirstName(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.08F,
                11.47F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getEmail(),
                        new Font(Font.FontFamily.COURIER, 6F)
                ),
                10.33F,
                12.69F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getLicense().getLicenseNumber(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.20F,
                12.98F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getLicense().getCategory(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.86F,
                13.27F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(report.getProfiles().get(1).getLicense().getExpires(),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                10.82F,
                13.57F
        );

        

        float[] coordY = {34.1F, 36.0F, 42.5F, 46.6F, 52.3F, 58.4F, 68.1F, 70.1F, 77.9F, 84.0F, 87.8F, 91.4F, 95.0F, 98.6F, 102.5F, 110.7F, 116.6F};
        int counter=0;
        for(float i : coordY){
            setPara(
                writer.getDirectContent(),
                new Phrase(report.getCircumstances()[0][counter] ? "x" : "",
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                52.1F,
                i
        );
            setPara(
                writer.getDirectContent(),
                new Phrase(report.getCircumstances()[1][counter] ? "x" : "",
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                86.6F,
                i
        );
            counter++;
        }
        
        document.close();

        return "C:\\Users\\SBZBN83\\Pictures\\" + report.getDateReportReceived().getTime() + "_aanrijdingsformulier.pdf";

    }

    public void setPara(PdfContentByte canvas, Phrase p, Float x, Float y) {
        x = x * 42.719F;
        y = 842 - (y * 42.775F);

        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, p, x, y, 0f);
    }
}
