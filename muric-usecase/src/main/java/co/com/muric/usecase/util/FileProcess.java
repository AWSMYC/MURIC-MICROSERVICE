package co.com.muric.usecase.util;

import co.com.muric.entities.util.FileData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FileProcess {

        public static List<FileData> readFile(String filePath) throws IOException {
            //TODO Falta dividir la estructura de los archivos segun el modelo pendiente

            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            Set<String> requiredColumns = new HashSet<>();
            requiredColumns.add("identificacion_credito_entidad");
            requiredColumns.add("tipo_identificacion");
            requiredColumns.add("numero_identificacion");
            requiredColumns.add("clave_atributo");
            requiredColumns.add("valor_atributo");
            requiredColumns.add("tipo_entidad");
            requiredColumns.add("codigo_entidad");
            requiredColumns.add("nombre_entidad");
            requiredColumns.add("fecha_corte");
            requiredColumns.add("fecha_generacion");
            requiredColumns.add("comentarios");
            requiredColumns.add("firma");
            requiredColumns.add("palabra_clave");
            requiredColumns.add("modalidad");
            requiredColumns.add("codigo_producto");
            requiredColumns.add("calidad_deudor");
            requiredColumns.add("fecha_desembolso");
            requiredColumns.add("fecha_vencimiento");
            requiredColumns.add("valor_desembolsado");
            requiredColumns.add("frecuencia_pago_capital");
            requiredColumns.add("frecuencia_pago_intereses");
            requiredColumns.add("tipo_tasa");
            requiredColumns.add("tipo_garantia");
            requiredColumns.add("moneda");
            requiredColumns.add("estado_registro");
            requiredColumns.add("calificacion_credito");
            requiredColumns.add("estado");
            requiredColumns.add("periodo_gracia");
            requiredColumns.add("dias_mora");
            requiredColumns.add("tasa_interes");
            requiredColumns.add("spread_tasa_interes");
            requiredColumns.add("saldo_capital");
            requiredColumns.add("saldo_intereses");
            requiredColumns.add("saldo_otros");
            requiredColumns.add("modelo_provisiones");
            requiredColumns.add("provision_prociclica");
            requiredColumns.add("provision_contraciclica");
            requiredColumns.add("provision_adicional_politica_entidad");
            requiredColumns.add("provision_otros");
            requiredColumns.add("cuota_esperada_capital");
            requiredColumns.add("cuota_esperada_intereses");
            requiredColumns.add("cuota_recibida_capital");
            requiredColumns.add("cuota_recibida_intereses");
            requiredColumns.add("valor_garantia");
            requiredColumns.add("fecha_garantia");
            requiredColumns.add("probabilidad_incumplimiento_credito");
            requiredColumns.add("perdida_dado_incumplimiento");

            Iterator<Row> rowIterator = sheet.iterator();
            Row headerRow = rowIterator.next();
            Iterator<Cell> cellIterator = headerRow.cellIterator();
            List<Integer> columnIndices = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String columnName = cell.getStringCellValue().trim().toLowerCase();

                if (requiredColumns.contains(columnName)) {
                    columnIndices.add(cell.getColumnIndex());
                }
            }

            List<FileData> excelDataList = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileData.FileDataBuilder dataBuilder = FileData.builder();

                for (Integer columnIndex : columnIndices) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getColumnIndex()) {
                            case 0:
                                dataBuilder.identificacionCreditoEntidad(getCellValueAsString(cell));
                                break;
                            case 1:
                                dataBuilder.tipoIdentificacion(getCellValueAsString(cell));
                                break;
                            case 2:
                                dataBuilder.numeroIdentificacion(getCellValueAsString(cell));
                                break;
                            case 3:
                                dataBuilder.claveAtributo(getCellValueAsString(cell));
                                break;
                            case 4:
                                dataBuilder.valorAtributo(getCellValueAsString(cell));
                                break;
                            case 5:
                                dataBuilder.tipoEntidad(getCellValueAsString(cell));
                                break;
                            case 6:
                                dataBuilder.codigoEntidad(getCellValueAsString(cell));
                                break;
                            case 7:
                                dataBuilder.nombreEntidad(getCellValueAsString(cell));
                                break;
                            case 8:
                                dataBuilder.fechaCorte(getCellValueAsDate(cell));
                                break;
                            case 9:
                                dataBuilder.fechaGeneracion(getCellValueAsDate(cell));
                                break;
                            case 10:
                                dataBuilder.comentarios(getCellValueAsString(cell));
                                break;
                            case 11:
                                dataBuilder.firma(getCellValueAsString(cell));
                                break;
                            case 12:
                                dataBuilder.palabraClave(getCellValueAsString(cell));
                                break;
                            case 13:
                                dataBuilder.modalidad(getCellValueAsString(cell));
                                break;
                            case 14:
                                dataBuilder.codigoProducto(getCellValueAsString(cell));
                                break;
                            case 15:
                                dataBuilder.calidadDeudor(getCellValueAsString(cell));
                                break;
                            case 16:
                                dataBuilder.fechaDesembolso(getCellValueAsDate(cell));
                                break;
                            case 17:
                                dataBuilder.fechaVencimiento(getCellValueAsDate(cell));
                                break;
                            case 18:
                                dataBuilder.valorDesembolsado(getCellValueAsDouble(cell));
                                break;
                            case 19:
                                dataBuilder.frecuenciaPagoCapital(getCellValueAsString(cell));
                                break;
                            case 20:
                                dataBuilder.frecuenciaPagoIntereses(getCellValueAsString(cell));
                                break;
                            case 21:
                                dataBuilder.tipoTasa(getCellValueAsString(cell));
                                break;
                            case 22:
                                dataBuilder.tipoGarantia(getCellValueAsString(cell));
                                break;
                            case 23:
                                dataBuilder.moneda(getCellValueAsString(cell));
                                break;
                            case 24:
                                dataBuilder.estadoRegistro(getCellValueAsString(cell));
                                break;
                            case 25:
                                dataBuilder.calificacionCredito(getCellValueAsString(cell));
                                break;
                            case 26:
                                dataBuilder.estado(getCellValueAsString(cell));
                                break;
                            case 27:
                                dataBuilder.periodoGracia(getCellValueAsInteger(cell));
                                break;
                            case 28:
                                dataBuilder.diasMora(getCellValueAsInteger(cell));
                                break;
                            case 29:
                                dataBuilder.tasaInteres(getCellValueAsDouble(cell));
                                break;
                            case 30:
                                dataBuilder.spreadTasaInteres(getCellValueAsDouble(cell));
                                break;
                            case 31:
                                dataBuilder.saldoCapital(getCellValueAsDouble(cell));
                                break;
                            case 32:
                                dataBuilder.saldoIntereses(getCellValueAsDouble(cell));
                                break;
                            case 33:
                                dataBuilder.saldoOtros(getCellValueAsDouble(cell));
                                break;
                            case 34:
                                dataBuilder.modeloProvisiones(getCellValueAsString(cell));
                                break;
                            case 35:
                                dataBuilder.provisionProciclica(getCellValueAsDouble(cell));
                                break;
                            case 36:
                                dataBuilder.provisionContraciclica(getCellValueAsDouble(cell));
                                break;
                            case 37:
                                dataBuilder.provisionAdicionalPoliticaEntidad(getCellValueAsDouble(cell));
                                break;
                            case 38:
                                dataBuilder.provisionOtros(getCellValueAsDouble(cell));
                                break;
                            case 39:
                                dataBuilder.cuotaEsperadaCapital(getCellValueAsDouble(cell));
                                break;
                            case 40:
                                dataBuilder.cuotaEsperadaIntereses(getCellValueAsDouble(cell));
                                break;
                            case 41:
                                dataBuilder.cuotaRecibidaCapital(getCellValueAsDouble(cell));
                                break;
                            case 42:
                                dataBuilder.cuotaRecibidaIntereses(getCellValueAsDouble(cell));
                                break;
                            case 43:
                                dataBuilder.valorGarantia(getCellValueAsDouble(cell));
                                break;
                            case 44:
                                dataBuilder.fechaGarantia(getCellValueAsDate(cell));
                                break;
                            case 45:
                                dataBuilder.probabilidadIncumplimientoCredito(getCellValueAsDouble(cell));
                                break;
                            case 46:
                                dataBuilder.perdidaDadoIncumplimiento(getCellValueAsDouble(cell));
                                break;
                            default:
                                break;
                        }
                    }
                }
                excelDataList.add(dataBuilder.build());
            }

            workbook.close();
            fis.close();

            return excelDataList;
        }

        private static String getCellValueAsString(Cell cell) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }

        private static LocalDate getCellValueAsDate(Cell cell) {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().toLocalDate();
            }
            return null;
        }

        private static Double getCellValueAsDouble(Cell cell) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            }
            return null;
        }

        private static Integer getCellValueAsInteger(Cell cell) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
            return null;
        }
}

