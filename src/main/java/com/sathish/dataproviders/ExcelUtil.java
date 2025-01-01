package com.sathish.dataproviders;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtil {

    /**
     * Reads data from an Excel sheet and returns it as a 2D Object array.
     *
     * @param filePath  The path to the Excel file
     * @param sheetName The name of the sheet to read
     * @return A 2D Object array containing the data
     * @throws IOException If there is an issue reading the file
     */
    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = null;
        Workbook workbook = null;

        try {
            // Open the Excel file
            fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            // Validate sheet content
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name \"" + sheetName + "\" does not exist.");
            }

            // Ensure the sheet has rows
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                throw new IllegalArgumentException("The sheet is empty or has no header row.");
            }

            Row headerRow = rowIterator.next(); // Skip the header row
            int totalCols = headerRow.getPhysicalNumberOfCells();
            int totalRows = sheet.getPhysicalNumberOfRows();

            // Initialize 2D array for data
            Object[][] data = new Object[totalRows - 1][totalCols];

            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                for (int j = 0; j < totalCols; j++) {
                    if (row == null || row.getCell(j) == null) {
                        data[i][j] = ""; // Default to an empty string if the cell is missing
                    } else {
                        data[i][j] = getCellValue(row.getCell(j)); // Get cell value as Object
                    }
                }
                i++;
            }

            return data;

        } finally {
            // Ensure resources are closed
            if (workbook != null) {
                workbook.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    /**
     * Helper method to handle different types of Excel cells.
     *
     * @param cell The cell to extract data from
     * @return The cell's value as an Object
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                try {
                    return cell.getNumericCellValue(); // Attempt to evaluate formula as numeric
                } catch (Exception e) {
                    return cell.getStringCellValue(); // Fall back to string if not numeric
                }
            default:
                return "";
        }
    }
}
