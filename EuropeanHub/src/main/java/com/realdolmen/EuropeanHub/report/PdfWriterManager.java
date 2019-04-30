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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PdfWriterManager {

    private Report report;

    public PdfWriterManager(Report report) {
        this.report = report;
    }

    public String generatePDF() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        Document document = new Document();
        String username = System.getenv("USERNAME");
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\" + username + "\\Pictures\\" + report.getDateReportReceived().getTime() + "_aanrijdingsformulier.pdf"));
        document.open();
        PdfContentByte canvas;
        canvas = writer.getDirectContentUnder();

        Image img = Image.getInstance("C:\\Users\\" + username + "\\Pictures\\aanrijdingsform.PNG");
        img.setAbsolutePosition(0F, 0F);
        img.scaleAbsolute(595F, 842F);

        canvas.addImage(img);

        writeCrashInformation(writer, report); // VOERTUIG A
        writeProfiles(writer, report);
        writeCircumstances(writer, report);

        placeImage(canvas, report.getSketch(), 8.27F, 3.79F, 2.82F, 17.68F, -90F);
        if (report.getSignatures() != null && report.getSignatures().length != 0) {
            placeImage(canvas, report.getSignatures()[0], 1F, 1.65F, 5.19F, 19.37F, 0F);
            placeImage(canvas, report.getSignatures()[1], 1F, 1.65F, 6.98F, 19.37F, 0F);
        }

        document.close();

        return "C:\\Users\\" + username + "\\Pictures\\" + report.getDateReportReceived().getTime() + "_aanrijdingsformulier.pdf";
    }

    public void setPara(PdfContentByte canvas, Phrase p, Float x, Float y) {
        x = x * 42.719F;
        y = 842 - (y * 42.775F);

        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, p, x, y, 0f);
    }

    public void placeImage(PdfContentByte canvas, String imageBase64, Float height, Float width, Float x, Float y, Float rotation) throws BadElementException, IOException, DocumentException {
        byte[] bytes = Base64.getMimeDecoder().decode(imageBase64);
        Image image = Image.getInstance(bytes);
        image.scaleAbsoluteHeight(height * 42.719F);
        image.scaleAbsoluteWidth(width * 42.775F);
        image.setRotationDegrees(rotation);
        image.setAbsolutePosition(x * 42.719F, 842 - (y * 42.775F));
        canvas.addImage(image);
    }

    private void writeCrashInformation(PdfWriter writer, Report report) {
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

    }

    private void writeCircumstances(PdfWriter writer, Report report) {
        float[] coordY = {3.41F, 3.60F, 4.25F, 4.66F, 5.23F, 5.84F, 6.41F, 7.01F, 7.79F, 8.40F, 8.78F, 9.14F, 9.50F, 9.86F, 10.25F, 11.07F, 11.66F};
        for (int i = 0; i < 17; i++) {
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getCircumstances()[0][i] ? "x" : "",
                            new Font(Font.FontFamily.COURIER, 9F, Font.BOLD)
                    ),
                    5.16F,
                    coordY[i]
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getCircumstances()[1][i] ? "x" : "",
                            new Font(Font.FontFamily.COURIER, 9F, Font.BOLD)
                    ),
                    8.66F,
                    coordY[i]
            );
        }
        int counterA = 0;
        int counterB = 0;
        for (boolean bool : report.getCircumstances()[0]) {
            if (bool == true) {
                counterA++;
            }
        }
        for (boolean bool : report.getCircumstances()[1]) {
            if (bool == true) {
                counterB++;
            }
        }
        setPara(
                writer.getDirectContent(),
                new Phrase(Integer.toString(counterA),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                5.16F,
                12.38F
        );
        setPara(
                writer.getDirectContent(),
                new Phrase(Integer.toString(counterB),
                        new Font(Font.FontFamily.COURIER, 9F)
                ),
                8.66F,
                12.38F
        );
    }

    private void writeProfiles(PdfWriter writer, Report report) throws IOException, DocumentException, DocumentException {
        for (int i = 0; i < 2; i++) {
            // Verzekeringsnemer 
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getLastName(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.10F + i * 8.7F,
                    3.07F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getEmail(),
                            new Font(Font.FontFamily.COURIER, 6F)
                    ),
                    1.61F + i * 8.7F,
                    4.34F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getFirstName(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.38F + i * 8.7F,
                    3.37F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getBrand() + " " + report.getProfiles().get(i).getVehicles().get(0).getModel(),
                            new Font(Font.FontFamily.COURIER, 6F)
                    ),
                    0.44F + i * 8.7F,
                    5.50F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getLicensePlate(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    0.44F + i * 8.7F,
                    6.03F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getCountry(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    0.44F + i * 8.7F,
                    6.58F
            );

            // VERZEKERING 
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getInsurance().getInsurer().getName(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.10F + i * 8.7F,
                    7.28F
            );

            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getInsurance().getInsuranceNumber(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.46F + i * 8.7F,
                    7.56F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getInsurance().getGreenCardNumber(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    2.22F + i * 8.7F,
                    7.87F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getInsurance().getExpires().getDate() + "/"
                            + (report.getProfiles().get(i).getVehicles().get(0).getInsurance().getExpires().getMonth() + 1) + "/"
                            + (report.getProfiles().get(i).getVehicles().get(0).getInsurance().getExpires().getYear() + 1900),
                            new Font(Font.FontFamily.COURIER, 6F)
                    ),
                    4.13F + i * 8.7F,
                    8.38F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getVehicles().get(0).getInsurance().getEmailAgency(),
                            new Font(Font.FontFamily.COURIER, 6F)
                    ),
                    1.63F + i * 8.7F,
                    10F
            );

            // BESTUURDER 
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getLastName(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.23F + i * 8.7F,
                    11.16F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getFirstName(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.42F + i * 8.7F,
                    11.47F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getEmail(),
                            new Font(Font.FontFamily.COURIER, 6F)
                    ),
                    1.67F + i * 8.7F,
                    12.69F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getLicense().getLicenseNumber(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    1.55F + i * 8.7F,
                    12.98F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getLicense().getCategory(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    2.18F + +i * 8.7F,
                    13.27F
            );
            setPara(
                    writer.getDirectContent(),
                    new Phrase(report.getProfiles().get(i).getLicense().getExpires(),
                            new Font(Font.FontFamily.COURIER, 9F)
                    ),
                    2.14F + i * 8.7F,
                    13.57F
            );
            //schade en opmerkingen
            placeImage(writer.getDirectContent(), report.getDamageIndications()[i], 1.82F, 2.39F, 0.32F + i * 10.90F, 16.45F, 0F);

            if (report.getRemarks()[i].length() > 43) {
                String line1Remarks = report.getRemarks()[i].substring(0, 43);
                if (line1Remarks.charAt(line1Remarks.length() - 1) != " ".charAt(0)
                        && report.getRemarks()[i].charAt(43) != " ".charAt(0)) {
                    line1Remarks += "-";
                }
                String line2Remarks = report.getRemarks()[i].substring(43);
                setPara(
                        writer.getDirectContent(),
                        new Phrase(line1Remarks,
                                new Font(Font.FontFamily.COURIER, 6F)
                        ),
                        0.36F + i * 9.19F,
                        18.44F
                );
                setPara(
                        writer.getDirectContent(),
                        new Phrase(line2Remarks,
                                new Font(Font.FontFamily.COURIER, 6F)
                        ),
                        0.36F + i * 9.19F,
                        18.67F
                );

            } else {
                setPara(
                        writer.getDirectContent(),
                        new Phrase(report.getRemarks()[i],
                                new Font(Font.FontFamily.COURIER, 6F)
                        ),
                        0.36F + i * 9.19F,
                        18.44F
                );
            }
        }

    }
}
