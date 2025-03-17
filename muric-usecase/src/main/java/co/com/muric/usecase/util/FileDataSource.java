package co.com.muric.usecase.util;

import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.util.StaticVariables;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class FileDataSource {

    static {
        IOUtils.setByteArrayMaxOverride(Integer.MAX_VALUE);
    }

    public static List<InformacionCredito> readSheetInformacionCredito(String filePath) throws IOException {
        return readSheetData(filePath, StaticVariables.SHEET_CREDIT_INFORMATION_NAME, FileDataSource::buildInformacionCredito);
    }

    public static List<AtributoCreditoDeuda> readSheetAtributoCreditoDeuda(String filePath) throws IOException {
        return readSheetData(filePath, StaticVariables.SHEET_CREDITS_DEBTS_NAME, FileDataSource::buildAtributoCreditoDeuda);
    }

    public static List<MovimientoCartera> readSheetMovimientoCartera(String filePath) throws IOException {
        return readSheetData(filePath, StaticVariables.SHEET_WALLET_MOVEMENTS_NAME, FileDataSource::buildMovimientoCartera);
    }

    private static <T> List<T> readSheetData(String filePath, String sheetName, Function<Map<String, Cell>, T> rowMapper) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                return Collections.emptyList();
            }
            Row headerRow = rowIterator.next();
            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
            }
            List<T> dataList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, Cell> cellMap = new HashMap<>();
                for (Map.Entry<String, Integer> entry : columnIndexMap.entrySet()) {
                    String columnKey = entry.getKey();
                    int columnIndex = entry.getValue();
                    cellMap.put(columnKey, row.getCell(columnIndex));
                }
                dataList.add(rowMapper.apply(cellMap));
            }
            return dataList;
        }
    }

    private static InformacionCredito buildInformacionCredito(Map<String, Cell> cellMap) {
        return InformacionCredito.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD)))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.TIPO_IDENTIFICACION)))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.NUMERO_IDENTIFICACION)))
                .modalidad(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.MODALIDAD)))
                .codigoProducto(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.CODIGO_PRODUCTO)))
                .calidadDeudor(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.CALIDAD_DEUDOR)))
                .fechaDesembolso(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FECHA_DESEMBOLSO)))
                .fechaVencimiento(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FECHA_VENCIMIENTO)))
                .valorDesembolsado(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.VALOR_DESEMBOLSADO)))
                .frecuenciaPagoCapital(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FRECUENCIA_PAGO_CAPITAL)))
                .frecuenciaPagoIntereses(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FRECUENCIA_PAGO_INTERESES)))
                .tipoTasa(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.TIPO_TASA)))
                .tipoGarantia(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.TIPO_GARANTIA)))
                .moneda(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.MONEDA)))
                .estadoRegistro(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.ESTADO_REGISTRO)))
                .build();
    }

    private static AtributoCreditoDeuda buildAtributoCreditoDeuda(Map<String, Cell> cellMap) {
        return AtributoCreditoDeuda.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD)))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.TIPO_IDENTIFICACION)))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.NUMERO_IDENTIFICACION)))
                .claveAtributo(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.CLAVE_ATRIBUTO)))
                .valorAtributo(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.VALOR_ATRIBUTO)))
                .build();
    }

    private static MovimientoCartera buildMovimientoCartera(Map<String, Cell> cellMap) {
        return MovimientoCartera.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD)))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.TIPO_IDENTIFICACION)))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.NUMERO_IDENTIFICACION)))
                .fechaCorte(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FECHA_CORTE)))
                .calificacionCredito(FormatFields.getCellValueAsString(cellMap.get(StaticVariables.CALIFICACION_CREDITO)))
                .estado(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.ESTADO)))
                .periodoGracia(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.PERIODO_GRACIA)))
                .diasMora(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.DIAS_MORA)))
                .tasaInteres(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.TASA_INTERES)))
                .spreadTasaInteres(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.SPREAD_TASA_INTERES)))
                .saldoCapital(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.SALDO_CAPITAL)))
                .saldoIntereses(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.SALDO_INTERESES)))
                .saldoOtros(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.SALDO_OTROS)))
                .modeloProvisiones(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.MODELO_PROVISIONES)))
                .provisionProciclica(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROVISION_PROCICLICA)))
                .provisionContraciclica(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROVISION_CONTRACICLICA)))
                .provisionAdicionalPoliticaEntidad(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROVISION_ADICIONAL_POLITICA_ENTIDAD)))
                .provisionOtros(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROVISION_OTROS)))
                .provisionTotal(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROVISION_TOTAL)))
                .cuotaEsperadaCapital(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.CUOTA_ESPERADA_CAPITAL)))
                .cuotaEsperadaIntereses(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.CUOTA_ESPERADA_INTERESES)))
                .cuotaRecibidaCapital(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.CUOTA_RECIBIDA_CAPITAL)))
                .cuotaRecibidaIntereses(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.CUOTA_RECIBIDA_INTERESES)))
                .valorGarantia(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.VALOR_GARANTIA)))
                .fechaGarantia(FormatFields.getCellValueAsInteger(cellMap.get(StaticVariables.FECHA_GARANTIA)))
                .probabilidadIncumplimientoCredito(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROBABILIDAD_INCUMPLIMIENTO_CREDITO)))
                .perdidaDadoIncumplimiento(FormatFields.getCellValueAsFloat(cellMap.get(StaticVariables.PROBABILIDAD_INCUMPLIMIENTO_CREDITO)))
                .build();
    }
    
}