package co.com.muric.usecase.util;

import co.com.muric.entities.util.AtributoCreditoDeuda;
import co.com.muric.entities.util.InformacionCredito;
import co.com.muric.entities.util.MovimientoCartera;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;

public class FileDataSource {

    static {
        IOUtils.setByteArrayMaxOverride(200_000_000);
    }

    public static List<InformacionCredito> readSheetInformacionCredito(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }
            Row headerRow = rowIterator.next();
            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
            }
            List<InformacionCredito> excelDataList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                InformacionCredito.InformacionCreditoBuilder dataBuilder = InformacionCredito.builder();
                columnIndexMap.forEach((key, index) -> {
                    Cell cell = row.getCell(index);
                    if (cell != null) {
                        switch (key.trim()) {
                            case "identificacion_credito_entidad":
                                dataBuilder.identificacionCreditoEntidad(getCellValueAsString(cell));
                                break;
                            case "tipo_identificacion":
                                dataBuilder.tipoIdentificacion(getCellValueAsInteger(cell));
                                break;
                            case "numero_identificacion":
                                dataBuilder.numeroIdentificacion(getCellValueAsString(cell));
                                break;
                            case "modalidad":
                                dataBuilder.modalidad(getCellValueAsInteger(cell));
                                break;
                            case "codigo_producto":
                                dataBuilder.codigoProducto(getCellValueAsInteger(cell));
                                break;
                            case "calidad_deudor":
                                dataBuilder.calidadDeudor(getCellValueAsInteger(cell));
                                break;
                            case "fecha_desembolso":
                                dataBuilder.fechaDesembolso(getCellValueAsInteger(cell));
                                break;
                            case "fecha_vencimiento":
                                dataBuilder.fechaVencimiento(getCellValueAsInteger(cell));
                                break;
                            case "valor_desembolsado":
                                dataBuilder.valorDesembolsado(getCellValueAsDouble(cell));
                                break;
                            case "frecuencia_pago_capital":
                                dataBuilder.frecuenciaPagoCapital(getCellValueAsInteger(cell));
                                break;
                            case "frecuencia_pago_intereses":
                                dataBuilder.frecuenciaPagoIntereses(getCellValueAsInteger(cell));
                                break;
                            case "tipo_tasa":
                                dataBuilder.tipoTasa(getCellValueAsString(cell));
                                break;
                            case "tipo_garantia":
                                dataBuilder.tipoGarantia(getCellValueAsInteger(cell));
                                break;
                            case "moneda":
                                dataBuilder.moneda(getCellValueAsString(cell));
                                break;
                            case "estado_registro":
                                dataBuilder.estadoRegistro(getCellValueAsString(cell));
                                break;
                        }
                    }
                });
                excelDataList.add(dataBuilder.build());
            }
            return excelDataList;
        }
    }

    public static List<AtributoCreditoDeuda> readSheetAtributoCreditoDeuda(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }
            Row headerRow = rowIterator.next();
            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
            }
            List<AtributoCreditoDeuda> excelDataList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                AtributoCreditoDeuda.AtributoCreditoDeudaBuilder dataBuilder = AtributoCreditoDeuda.builder();
                columnIndexMap.forEach((key, index) -> {
                    Cell cell = row.getCell(index);
                    if (cell != null) {
                        switch (key.trim()) {
                            case "identificacion_credito_entidad":
                                dataBuilder.identificacionCreditoEntidad(getCellValueAsString(cell));
                                break;
                            case "tipo_identificacion":
                                dataBuilder.tipoIdentificacion(getCellValueAsInteger(cell));
                                break;
                            case "numero_identificacion":
                                dataBuilder.numeroIdentificacion(getCellValueAsString(cell));
                                break;
                            case "modalidad":
                                dataBuilder.modalidad(getCellValueAsInteger(cell));
                                break;
                            case "codigo_producto":
                                dataBuilder.codigoProducto(getCellValueAsInteger(cell));
                                break;
                            case "calidad_deudor":
                                dataBuilder.calidadDeudor(getCellValueAsInteger(cell));
                                break;
                            case "fecha_desembolso":
                                dataBuilder.fechaDesembolso(getCellValueAsInteger(cell));
                                break;
                            case "fecha_vencimiento":
                                dataBuilder.fechaVencimiento(getCellValueAsInteger(cell));
                                break;
                            case "valor_desembolsado":
                                dataBuilder.valorDesembolsado(getCellValueAsDouble(cell));
                                break;
                            case "frecuencia_pago_capital":
                                dataBuilder.frecuenciaPagoCapital(getCellValueAsInteger(cell));
                                break;
                            case "frecuencia_pago_intereses":
                                dataBuilder.frecuenciaPagoIntereses(getCellValueAsInteger(cell));
                                break;
                            case "tipo_tasa":
                                dataBuilder.tipoTasa(getCellValueAsString(cell));
                                break;
                            case "tipo_garantia":
                                dataBuilder.tipoGarantia(getCellValueAsInteger(cell));
                                break;
                            case "moneda":
                                dataBuilder.moneda(getCellValueAsString(cell));
                                break;
                            case "estado_registro":
                                dataBuilder.estadoRegistro(getCellValueAsString(cell));
                                break;
                        }
                    }
                });
                excelDataList.add(dataBuilder.build());
            }
            return excelDataList;
        }
    }

    public static List<MovimientoCartera> readSheetMovimientoCartera(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }
            Row headerRow = rowIterator.next();
            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
            }
            List<MovimientoCartera> excelDataList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                MovimientoCartera.MovimientoCarteraBuilder dataBuilder = MovimientoCartera.builder();
                columnIndexMap.forEach((key, index) -> {
                    Cell cell = row.getCell(index);
                    if (cell != null) {
                        switch (key.trim()) {
                            case "identificacion_credito_entidad":
                                dataBuilder.identificacionCreditoEntidad(getCellValueAsString(cell));
                                break;
                            case "tipo_identificacion":
                                dataBuilder.tipoIdentificacion(getCellValueAsInteger(cell));
                                break;
                            case "numero_identificacion":
                                dataBuilder.numeroIdentificacion(getCellValueAsString(cell));
                                break;
                            case "modalidad":
                                dataBuilder.modalidad(getCellValueAsInteger(cell));
                                break;
                            case "codigo_producto":
                                dataBuilder.codigoProducto(getCellValueAsInteger(cell));
                                break;
                            case "calidad_deudor":
                                dataBuilder.calidadDeudor(getCellValueAsInteger(cell));
                                break;
                            case "fecha_desembolso":
                                dataBuilder.fechaDesembolso(getCellValueAsInteger(cell));
                                break;
                            case "fecha_vencimiento":
                                dataBuilder.fechaVencimiento(getCellValueAsInteger(cell));
                                break;
                            case "valor_desembolsado":
                                dataBuilder.valorDesembolsado(getCellValueAsDouble(cell));
                                break;
                            case "frecuencia_pago_capital":
                                dataBuilder.frecuenciaPagoCapital(getCellValueAsInteger(cell));
                                break;
                            case "frecuencia_pago_intereses":
                                dataBuilder.frecuenciaPagoIntereses(getCellValueAsInteger(cell));
                                break;
                            case "tipo_tasa":
                                dataBuilder.tipoTasa(getCellValueAsString(cell));
                                break;
                            case "tipo_garantia":
                                dataBuilder.tipoGarantia(getCellValueAsInteger(cell));
                                break;
                            case "moneda":
                                dataBuilder.moneda(getCellValueAsString(cell));
                                break;
                            case "estado_registro":
                                dataBuilder.estadoRegistro(getCellValueAsString(cell));
                                break;
                        }
                    }
                });
                excelDataList.add(dataBuilder.build());
            }
            return excelDataList;
        }
    }

    private static String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private static LocalDate getCellValueAsDate(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }
        return null;
    }

    private static Double getCellValueAsDouble(Cell cell) {
        return (cell.getCellType() == CellType.NUMERIC) ? cell.getNumericCellValue() : null;
    }

    private static Integer getCellValueAsInteger(Cell cell) {
        if (cell == null) return null;

        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield null; // No se puede convertir a Integer
                }
            }
            default -> null;
        };
    }
}
