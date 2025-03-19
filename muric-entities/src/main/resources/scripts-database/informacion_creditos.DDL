-- muric_schema.informacion_creditos definition

-- Drop table

-- DROP TABLE muric_schema.informacion_creditos;

CREATE TABLE muric_schema.informacion_creditos
(
    identificacion_credito_entidad int8 NULL,
    tipo_identificacion            int4 NULL,
    numero_identificacion          int8 NULL,
    modalidad                      int4 NULL,
    codigo_producto                int4 NULL,
    calidad_deudor                 int4 NULL,
    fecha_desembolso               int4 NULL,
    fecha_vencimiento              int4 NULL,
    valor_desembolsado             numeric NULL,
    frecuencia_pago_capital        int4 NULL,
    frecuencia_pago_intereses      int4 NULL,
    tipo_tasa                      text NULL,
    tipo_garantia                  int4 NULL,
    moneda                         text NULL,
    estado_registro                text NULL,
    id                             int8 DEFAULT nextval('muric_schema.insumos_credito_id_seq'::regclass) NOT NULL
);