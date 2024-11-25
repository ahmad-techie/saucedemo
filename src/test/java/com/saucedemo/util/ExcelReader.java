package com.saucedemo.util;

import com.saucedemo.constants.SharedConstants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelReader extends Logger implements SharedConstants {

    private static Workbook workbook = null;

    static {
        try {
            FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
            workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public Object[][] readData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet with name " + sheetName + " does not exist.");
        }

        List<Object[]> data = new ArrayList<>();
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header row
            Row row = sheet.getRow(i);
            if (row != null) {
                String firstName = getCellValue(row.getCell(0));
                String lastName = getCellValue(row.getCell(1));
                String zipCode = getCellValue(row.getCell(2));

                data.add(new Object[]{firstName, lastName, zipCode});
            }
        }

        return data.toArray(new Object[0][]);
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
}
