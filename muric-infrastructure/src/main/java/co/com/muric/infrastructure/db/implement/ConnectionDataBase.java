package co.com.muric.infrastructure.db.implement;

import co.com.muric.entities.model.database.DataSource;
import co.com.muric.entities.model.database.ResultSetModel;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.db.interfaces.IConnectionDataBase;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ConnectionDataBase implements IConnectionDataBase {

    private static final Logger logger = LogManager.getLogger(ConnectionDataBase.class);
    private static final int THREAD_POOL_SIZE = 10;

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Override
    public ResultSetModel executeQuery(DataSource dataSource) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        List<InformacionCredito> informacionCreditoList = Collections.synchronizedList(new ArrayList<>());
        List<AtributoCreditoDeuda> atributoCreditoDeudaList = Collections.synchronizedList(new ArrayList<>());
        List<MovimientoCartera> movimientoCarteraList = Collections.synchronizedList(new ArrayList<>());

        for (String tableName : dataSource.getTableNameList()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                String sql = MessageFormat.format(StaticVariables.SELECT_TABLE, dataSource.getSchema(), tableName);
                try (Connection connection = DriverManager.getConnection(dataSource.getHost(), dataSource.getUser(), dataSource.getPassword());
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(sql)) {

                    while (resultSet.next()) {
                        switch (tableName) {
                            case StaticVariables.INFORMACION_CREDITO_ENTIDAD_TABLE_NAME:
                                informacionCreditoList.add(InformacionCredito.builder()
                                        .identificacionCreditoEntidad(resultSet.getString(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD))
                                        .tipoIdentificacion(resultSet.getInt(StaticVariables.TIPO_IDENTIFICACION))
                                        .numeroIdentificacion(resultSet.getString(StaticVariables.NUMERO_IDENTIFICACION))
                                        .modalidad(resultSet.getInt(StaticVariables.MODALIDAD))
                                        .codigoProducto(resultSet.getInt(StaticVariables.CODIGO_PRODUCTO))
                                        .calidadDeudor(resultSet.getInt(StaticVariables.CALIDAD_DEUDOR))
                                        .fechaDesembolso(resultSet.getInt(StaticVariables.FECHA_DESEMBOLSO))
                                        .fechaVencimiento(resultSet.getInt(StaticVariables.FECHA_VENCIMIENTO))
                                        .valorDesembolsado(resultSet.getFloat(StaticVariables.VALOR_DESEMBOLSADO))
                                        .frecuenciaPagoCapital(resultSet.getInt(StaticVariables.FRECUENCIA_PAGO_CAPITAL))
                                        .frecuenciaPagoIntereses(resultSet.getInt(StaticVariables.FRECUENCIA_PAGO_INTERESES))
                                        .tipoTasa(resultSet.getString(StaticVariables.TIPO_TASA))
                                        .tipoGarantia(resultSet.getInt(StaticVariables.TIPO_GARANTIA))
                                        .moneda(resultSet.getString(StaticVariables.MONEDA))
                                        .estadoRegistro(resultSet.getString(StaticVariables.ESTADO_REGISTRO))
                                        .build());
                                break;
                            case StaticVariables.ATRIBUTOS_CREDITOS_TABLE_NAME:
                                atributoCreditoDeudaList.add(AtributoCreditoDeuda.builder()
                                        .identificacionCreditoEntidad(resultSet.getString(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD))
                                        .tipoIdentificacion(resultSet.getInt(StaticVariables.TIPO_IDENTIFICACION))
                                        .numeroIdentificacion(resultSet.getString(StaticVariables.NUMERO_IDENTIFICACION))
                                        .claveAtributo(resultSet.getInt(StaticVariables.CLAVE_ATRIBUTO))
                                        .valorAtributo(resultSet.getString(StaticVariables.VALOR_ATRIBUTO))
                                        .build());
                                break;
                            case StaticVariables.MOVIMIENTOS_CARTERA_TABLE_NAME:
                                movimientoCarteraList.add(MovimientoCartera.builder()
                                        .identificacionCreditoEntidad(resultSet.getString(StaticVariables.IDENTIFICACION_CREDITO_ENTIDAD))
                                        .tipoIdentificacion(resultSet.getInt(StaticVariables.TIPO_IDENTIFICACION))
                                        .numeroIdentificacion(resultSet.getString(StaticVariables.NUMERO_IDENTIFICACION))
                                        .fechaCorte(resultSet.getInt(StaticVariables.FECHA_CORTE))
                                        .calificacionCredito(resultSet.getString(StaticVariables.CALIFICACION_CREDITO))
                                        .estado(resultSet.getInt(StaticVariables.ESTADO))
                                        .periodoGracia(resultSet.getInt(StaticVariables.PERIODO_GRACIA))
                                        .diasMora(resultSet.getInt(StaticVariables.DIAS_MORA))
                                        .tasaInteres(resultSet.getFloat(StaticVariables.TASA_INTERES))
                                        .spreadTasaInteres(resultSet.getFloat(StaticVariables.SPREAD_TASA_INTERES))
                                        .saldoCapital(resultSet.getFloat(StaticVariables.SALDO_CAPITAL))
                                        .saldoIntereses(resultSet.getFloat(StaticVariables.SALDO_INTERESES))
                                        .saldoOtros(resultSet.getFloat(StaticVariables.SALDO_OTROS))
                                        .modeloProvisiones(resultSet.getInt(StaticVariables.MODELO_PROVISIONES))
                                        .provisionProciclica(resultSet.getFloat(StaticVariables.PROVISION_PROCICLICA))
                                        .provisionContraciclica(resultSet.getFloat(StaticVariables.PROVISION_CONTRACICLICA))
                                        .provisionAdicionalPoliticaEntidad(resultSet.getFloat(StaticVariables.PROVISION_ADICIONAL_POLITICA_ENTIDAD))
                                        .provisionOtros(resultSet.getFloat(StaticVariables.PROVISION_OTROS))
                                        .provisionTotal(resultSet.getFloat(StaticVariables.PROVISION_TOTAL))
                                        .cuotaEsperadaCapital(resultSet.getFloat(StaticVariables.CUOTA_ESPERADA_CAPITAL))
                                        .cuotaEsperadaIntereses(resultSet.getFloat(StaticVariables.CUOTA_ESPERADA_INTERESES))
                                        .cuotaRecibidaCapital(resultSet.getFloat(StaticVariables.CUOTA_RECIBIDA_CAPITAL))
                                        .cuotaRecibidaIntereses(resultSet.getFloat(StaticVariables.CUOTA_RECIBIDA_INTERESES))
                                        .valorGarantia(resultSet.getFloat(StaticVariables.VALOR_GARANTIA))
                                        .fechaGarantia(resultSet.getInt(StaticVariables.FECHA_GARANTIA))
                                        .probabilidadIncumplimientoCredito(resultSet.getFloat(StaticVariables.PROBABILIDAD_INCUMPLIMIENTO_CREDITO))
                                        .perdidaDadoIncumplimiento(resultSet.getFloat(StaticVariables.PERDIDA_DADO_INCUMPLIMIENTO))
                                        .build());
                                break;
                        }
                    }
                    logger.info(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_SUCCESS, tableName));
                } catch (Exception e) {
                    logger.error(MessageFormat.format(StaticVariables.RESULSET_ERROR, tableName, e.getMessage()));
                }
            }, executorService);

            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return ResultSetModel.builder()
                .informacionCreditoList(informacionCreditoList)
                .atributoCreditoDeudaList(atributoCreditoDeudaList)
                .movimientoCarteraList(movimientoCarteraList)
                .build();
    }

    @PreDestroy
    public void shutdownExecutor() {
        executorService.shutdown();
    }

}