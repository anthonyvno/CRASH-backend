/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public static ByteArrayInputStream reportToExcel(Report report) throws IOException {
        String[] columnsReport = {"reportId", "dateCrash", "dateReportReceived", "street", "streetNumber", "postalCode","city","country"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Report");

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

            int rowIdx = 1;
            Row row = sheet.createRow(rowIdx);

            row.createCell(0).setCellValue(report.getId());
            row.createCell(1).setCellValue(report.getCity());
            

            //Auto-size all the above columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
