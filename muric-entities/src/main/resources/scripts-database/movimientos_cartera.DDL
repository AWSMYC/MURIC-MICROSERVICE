-- muric_schema.movimientos_cartera definition

-- Drop table

-- DROP TABLE muric_schema.movimientos_cartera;

CREATE TABLE muric_schema.movimientos_cartera
(
    identificacion_credito_entidad       int8 NULL,
    id                                   int8 DEFAULT nextval('muric_schema.insumos_credito_id_seq'::regclass) NOT NULL,
    tipo_identificacion                  int4 NULL,
    numero_identificacion                int4 NULL,
    calificacion_credito                 text NULL,
    estado                               int4 NULL,
    periodo_gracia                       int4 NULL,
    dias_mora                            int4 NULL,
    tasa_interes                         numeric NULL,
    spread_tasa_interes                  numeric NULL,
    saldo_capital                        numeric NULL,
    saldo_intereses                      numeric NULL,
    saldo_otros                          numeric NULL,
    modelo_provisiones                   int4 NULL,
    provision_prociclica                 int4 NULL,
    provision_contraciclica              int4 NULL,
    provision_otros                      int4 NULL,
    provision_total                      int4 NULL,
    cuota_esperada_capital               int8 NULL,
    cuota_esperada_intereses             int8 NULL,
    cuota_recibida_capital               int8 NULL,
    cuota_recibida_intereses             int8 NULL,
    valor_garantia                       int8 NULL,
    probabilidad_incumplimiento_credito  int4 NULL,
    perdida_dado_incumplimiento          int4 NULL,
    provision_adicional_politica_entidad int4 NULL,
    fecha_corte                          int4 NULL,
    fecha_garantia                       int4 NULL
);