package co.com.muric.usecase.util;

import org.apache.poi.ss.usermodel.*;

public class FormatFields {

    public static String getCellValueAsString(Cell cell) {
        return (cell == null) ? null : switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> null;
        };
    }

    public static Float getCellValueAsFloat(Cell cell) {
        if (cell == null) {
            return 0.0f;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return (float) cell.getNumericCellValue();
            case STRING:
                String value = cell.getStringCellValue().trim().replace(",", ".");
                try {
                    return Float.parseFloat(value);
                } catch (NumberFormatException e) {
                    return 0.0f;
                }
            case FORMULA:
                try {
                    return (float) cell.getNumericCellValue();
                } catch (IllegalStateException e) {
                    return 0.0f;
                }
            default:
                return 0.0f;
        }
    }

    public static Integer getCellValueAsInteger(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield null;
                }
            }
            default -> null;
        };
    }
}