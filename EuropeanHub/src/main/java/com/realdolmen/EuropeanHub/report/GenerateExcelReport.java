/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

import com.realdolmen.EuropeanHub.profile.ProfileEU;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author AVOBN94
 */
public class GenerateExcelReport {

    private String[] columnsReport = {"reportId", "dateCrash", "dateReportReceived", "street", "streetNumber", "postalCode", "city", "country",
        "firstNameA", " lastNameA", "emailA",
        "licenseCategoryA", "licenseNumberA", "licenseExpiresA",
        "vehicleCountryA", "licensePlateA", "vehicleBrandA", "vehicleModelA", "vehicleTypeA",
        "insuranceNumberA", "greenCardNumberA", "emailAgencyA", "insuranceExpiresA", "phoneAgencyA",
        "insurerCountryA", "insurerNameA",
        "firstNameB", " lastNameB", "emailB",
        "licenseCategoryB", "licenseNumberB", "licenseExpiresB",
        "vehicleCountryB", "licensePlateB", "vehicleBrandB", "vehicleModelB", "vehicleTypeB",
        "insuranceNumberB", "greenCardNumberB", "emailBgencyB", "insuranceExpiresB", "phoneBgencyB",
        "insurerCountryB", "insurerNameB"};

    public ByteArrayInputStream reportToExcel(List<Report> reports) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Reports");

            writeColumnHeaders(sheet, workbook);
            writeReportRows(sheet, reports);

            //int rowIdx = 1;
            //Auto-size all the above columns
            for (int i = 0; i < columnsReport.length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private void writeColumnHeaders(Sheet sheet, Workbook workbook) {

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Header Row
        Row headerRow = sheet.createRow(0);

        // Table Header
        for (int col = 0; col < columnsReport.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columnsReport[col]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void writeReportRows(Sheet sheet, List<Report> reports) {
        int counter = 1;

        for (Report report : reports) {
            int cellColumn = 0;
            Row row = sheet.createRow(counter);
            row.createCell(cellColumn++).setCellValue(report.getId());
            row.createCell(cellColumn++).setCellValue(report.getDateCrash().toString());
            row.createCell(cellColumn++).setCellValue(report.getDateReportReceived().toString());
            row.createCell(cellColumn++).setCellValue(report.getStreet());
            row.createCell(cellColumn++).setCellValue(report.getStreetNumber());
            row.createCell(cellColumn++).setCellValue(report.getPostalCode());
            row.createCell(cellColumn++).setCellValue(report.getCity());
            row.createCell(cellColumn++).setCellValue(report.getCountry());
            for (ProfileEU p : report.getProfiles()) {
                row.createCell(cellColumn++).setCellValue(p.getFirstName());
                row.createCell(cellColumn++).setCellValue(p.getLastName());
                row.createCell(cellColumn++).setCellValue(p.getEmail());
                row.createCell(cellColumn++).setCellValue(p.getLicense().getCategory());
                row.createCell(cellColumn++).setCellValue(p.getLicense().getLicenseNumber());
                row.createCell(cellColumn++).setCellValue(p.getLicense().getExpires());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getCountry());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getLicensePlate());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getBrand());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getModel());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getType());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getInsuranceNumber());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getGreenCardNumber());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getEmailAgency());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getExpires().toString());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getPhoneAgency());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getInsurer().getCountry());
                row.createCell(cellColumn++).setCellValue(p.getVehicles().get(0).getInsurance().getInsurer().getName());
            }
            counter++;

        }

        //
        //row.createCell(1).setCellValue(report.getCity());
    }

}
