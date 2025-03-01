package co.com.muric.usecase.util;

import co.com.muric.entities.util.AtributoCreditoDeuda;
import co.com.muric.entities.util.InformacionCredito;
import co.com.muric.entities.util.MovimientoCartera;
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
        IOUtils.setByteArrayMaxOverride(200_000_000);
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
                    cellMap.put(entry.getKey(), row.getCell(entry.getValue()));
                }
                dataList.add(rowMapper.apply(cellMap));
            }
            return dataList;
        }
    }

    private static InformacionCredito buildInformacionCredito(Map<String, Cell> cellMap) {
        return InformacionCredito.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get("identificacion_credito_entidad")))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get("tipo_identificacion")))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get("numero_identificacion")))
                .modalidad(FormatFields.getCellValueAsInteger(cellMap.get("modalidad")))
                .codigoProducto(FormatFields.getCellValueAsInteger(cellMap.get("codigo_producto")))
                .calidadDeudor(FormatFields.getCellValueAsInteger(cellMap.get("calidad_deudor")))
                .fechaDesembolso(FormatFields.getCellValueAsInteger(cellMap.get("fecha_desembolso")))
                .fechaVencimiento(FormatFields.getCellValueAsInteger(cellMap.get("fecha_vencimiento")))
                .valorDesembolsado(FormatFields.getCellValueAsFloat(cellMap.get("valor_desembolsado")))
                .frecuenciaPagoCapital(FormatFields.getCellValueAsInteger(cellMap.get("frecuencia_pago_capital")))
                .frecuenciaPagoIntereses(FormatFields.getCellValueAsInteger(cellMap.get("frecuencia_pago_intereses")))
                .tipoTasa(FormatFields.getCellValueAsString(cellMap.get("tipo_tasa")))
                .tipoGarantia(FormatFields.getCellValueAsInteger(cellMap.get("tipo_garantia")))
                .moneda(FormatFields.getCellValueAsString(cellMap.get("moneda")))
                .estadoRegistro(FormatFields.getCellValueAsString(cellMap.get("estado_registro")))
                .build();
    }

    private static AtributoCreditoDeuda buildAtributoCreditoDeuda(Map<String, Cell> cellMap) {
        return AtributoCreditoDeuda.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get("identificacion_credito_entidad")))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get("tipo_identificacion")))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get("numero_identificacion")))
                .claveAtributo(FormatFields.getCellValueAsInteger(cellMap.get("clave_atributo")))
                .valorAtributo(FormatFields.getCellValueAsString(cellMap.get("valor_atributo")))
                .build();
    }

    private static MovimientoCartera buildMovimientoCartera(Map<String, Cell> cellMap) {
        return MovimientoCartera.builder()
                .identificacionCreditoEntidad(FormatFields.getCellValueAsString(cellMap.get("identificacion_credito_entidad")))
                .tipoIdentificacion(FormatFields.getCellValueAsInteger(cellMap.get("tipo_identificacion")))
                .numeroIdentificacion(FormatFields.getCellValueAsString(cellMap.get("numero_identificacion")))
                .fechaCorte(FormatFields.getCellValueAsInteger(cellMap.get("fecha_corte")))
                .calificacionCredito(FormatFields.getCellValueAsString(cellMap.get("calificacion_credito")))
                .estado(FormatFields.getCellValueAsInteger(cellMap.get("estado")))
                .periodoGracia(FormatFields.getCellValueAsInteger(cellMap.get("periodo_gracia")))
                .diasMora(FormatFields.getCellValueAsInteger(cellMap.get("dias_mora")))
                .tasaInteres(FormatFields.getCellValueAsFloat(cellMap.get("tasa_interes")))
                .spreadTasaInteres(FormatFields.getCellValueAsFloat(cellMap.get("spread_tasa_interes")))
                .saldoCapital(FormatFields.getCellValueAsFloat(cellMap.get("saldo_capital")))
                .saldoIntereses(FormatFields.getCellValueAsFloat(cellMap.get("saldo_intereses")))
                .saldoOtros(FormatFields.getCellValueAsFloat(cellMap.get("saldo_otros")))
                .modeloProvisiones(FormatFields.getCellValueAsInteger(cellMap.get("modelo_provisiones")))
                .provisionProciclica(FormatFields.getCellValueAsFloat(cellMap.get("provision_prociclica")))
                .provisionContraciclica(FormatFields.getCellValueAsFloat(cellMap.get("provision_contraciclica")))
                .provisionAdicionalPoliticaEntidad(FormatFields.getCellValueAsFloat(cellMap.get("provision_adicional_politica_entidad")))
                .provisionOtros(FormatFields.getCellValueAsFloat(cellMap.get("provision_otros")))
                .cuotaEsperadaCapital(FormatFields.getCellValueAsFloat(cellMap.get("cuota_esperada_capital")))
                .cuotaEsperadaIntereses(FormatFields.getCellValueAsFloat(cellMap.get("cuota_esperada_intereses")))
                .cuotaRecibidaCapital(FormatFields.getCellValueAsFloat(cellMap.get("cuota_recibida_capital")))
                .cuotaRecibidaIntereses(FormatFields.getCellValueAsFloat(cellMap.get("cuota_recibida_intereses")))
                .valorGarantia(FormatFields.getCellValueAsFloat(cellMap.get("valor_garantia")))
                .fechaGarantia(FormatFields.getCellValueAsInteger(cellMap.get("fecha_garantia")))
                .probabilidadIncumplimientoCredito(FormatFields.getCellValueAsFloat(cellMap.get("probabilidad_incumplimiento_credito")))
                .perdidaDadoIncumplimiento(FormatFields.getCellValueAsFloat(cellMap.get("perdida_dado_incumplimiento")))
                .build();
    }
    
}