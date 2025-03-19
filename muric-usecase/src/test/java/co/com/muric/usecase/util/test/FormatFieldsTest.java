package co.com.muric.usecase.util.test;

import co.com.muric.usecase.util.FormatFields;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FormatFieldsTest {

    private Cell stringCell, numericCell, booleanCell, emptyCell, nullCell;

    @Before
    public void setUp() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        stringCell = row.createCell(0, CellType.STRING);
        stringCell.setCellValue("Test String");
        numericCell = row.createCell(1, CellType.NUMERIC);
        numericCell.setCellValue(123.45);
        booleanCell = row.createCell(2, CellType.BOOLEAN);
        booleanCell.setCellValue(true);
        emptyCell = row.createCell(3, CellType.BLANK);
    }

    @Test
    public void testGetCellValueAsString() {
        assertEquals("Test String", FormatFields.getCellValueAsString(stringCell));
        assertEquals("123.45", FormatFields.getCellValueAsString(numericCell));
        assertEquals("true", FormatFields.getCellValueAsString(booleanCell));
        assertNull(FormatFields.getCellValueAsString(emptyCell));
        assertNull(FormatFields.getCellValueAsString(nullCell));
    }

    @Test
    public void testGetCellValueAsFloat() {
        assertEquals(123.45f, FormatFields.getCellValueAsFloat(numericCell), 0.01);
        assertEquals(0.0f, FormatFields.getCellValueAsFloat(emptyCell), 0.01);
        assertEquals(0.0f, FormatFields.getCellValueAsFloat(nullCell), 0.01);
        Cell invalidFloatCell = stringCell;
        invalidFloatCell.setCellValue("abc");
        assertEquals(0.0f, FormatFields.getCellValueAsFloat(invalidFloatCell), 0.01);
    }

    @Test
    public void testGetCellValueAsInteger() {
        Cell integerCell = numericCell;
        integerCell.setCellValue(10);
        assertEquals(Integer.valueOf(10), FormatFields.getCellValueAsInteger(integerCell));

        Cell stringNumberCell = stringCell;
        stringNumberCell.setCellValue("42");
        assertEquals(Integer.valueOf(42), FormatFields.getCellValueAsInteger(stringNumberCell));

        stringNumberCell.setCellValue("not a number");
        assertNull(FormatFields.getCellValueAsInteger(stringNumberCell));

        assertNull(FormatFields.getCellValueAsInteger(emptyCell));
        assertNull(FormatFields.getCellValueAsInteger(nullCell));
    }
}
